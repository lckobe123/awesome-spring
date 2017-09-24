package com.ternence.spring.drools;


import com.ternence.spring.drools.domain.ProductDomain;
import org.drools.core.definitions.impl.KnowledgePackageImpl;
import org.junit.Test;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class DroolsTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testHelloWorld() {
        //主要用来获取源文件，比如*drl文件,*.bpmn2文件,*xls文件
        //并根据ResourceType把这些资源转换为KnowledgePackage
        KnowledgeBuilder builder = KnowledgeBuilderFactory.
                newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("rules/discount.drl"), ResourceType.DRL);
        if (builder.hasErrors()) {
            logger.info(builder.getErrors().toString());
        } else {
            logger.info("没毛病");
        }
        //6.x版本还叫KnowledgePackage，7.x版本这个名字就变成KiePackage了，这点兼容性都做不到吗？
        //6.x版本也没有RuleBase这个类了，所以说drools的版本之间不是前后兼容的
        Collection<KnowledgePackage> knowledgePackages = builder.getKnowledgePackages();
        //把这些KnowledgePackage添加到规则库当中
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgePackages);

        StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
        ProductDomain product = new ProductDomain();
        product.setType(ProductDomain.DIAMOND);
        //将fact插入引擎中
        session.insert(product);
        session.fireAllRules();
        session.dispose();

        logger.info("The discount for the product " + product.getType()
                + " is " + product.getDiscount() + "%");
    }
}
