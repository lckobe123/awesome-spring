package com.ternence.spring.drools;

import com.ternence.spring.drools.fact.Person;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Drools的测试案例
 */
public class DroolsTestsCase {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 测试Drools的运行流程，使用Drools的API来加载，编译和运行这些规则，并从这些规则中得到结果
     * <p>
     * 值得注意的是，编译的时候会有一个警告
     * Warning:(69, 33) java: '_' 用作标识符
     * (Java SE 8 之后的发行版中可能不支持使用 '_' 作为标识符)
     * </p>
     * 所以目测Drools可能只会在Java8上运行，Java9改天去测试一下行不行
     */
    @Test
    public void testGettingStartRules() {
        //1:新建一个知识库构建对象
        // The KnowledgeBuilder is responsible for taking source file,
        // such as a .drl file,a .bpmn2 file or an .xsl file ,
        //把他们转换成KnowledgeBase可以使用的规则和流程定义的KnowledgePackage
        // and turning them into a KnowledgePackage of rule and process definitions Which a KnowledgeBase can consume.
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // add resource to the KnowledgeBuilder
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules/person_rule.drl"), ResourceType.DRL);
        //taking KnowledgePackage from KnowledgeBuild
        //在Drools7中KnowledgePackage叫做KiePackage
        //如果规则文件中定义的package和Fact所在的包不一样，那么这里会得到两个KiePackage对象，
        //第一个保存用户定义的所有规则
        //第二个保存规则中使用的fact的包，导入package申明的包下的所有类
        /*Collection<KiePackage> kiePackages = knowledgeBuilder.getKnowledgePackages();
        for (KiePackage kiePackage : kiePackages) {
            logger.debug(kiePackage.toString());
        }*/
        // create instance of Knowledge，rename to KieBase in Drools 7.x
        KieBase kieBase = knowledgeBuilder.newKieBase();
        // deploy KiePackage to KieBase
        // 在Drools5.x的时候这里需要把KnowledgePackage添加到kieBase对象中
        // 但是在Drools7中可以不用这样做，可以直接使用kieBase.getKiePackage获取到package
        // 事实上是因为在构造KieBase对象的时候就自动加入了package，代码如下，这样做是正确的，
        // 本来package的信息就在context中了，让用户自己再加一遍显得十分笨拙，代码冗余
        /*InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(conf);
        kbase.addPackages( Arrays.asList( getPackages() ) );*/

        //KiePackage KiePackage = kieBase.getKiePackage("drools.rules.person");
        //logger.debug(KiePackage.toString());

        //获取KieSession，KieSession是一个到规则引擎的连接，通过这个对象我们可以操作规则引擎的API
        //获取我们想要的结果,StatelessSession可以避免调用dispose方法，比较便利
        //这里使用默认KieServices去获取session对象
        StatelessKieSession session = kieBase.newStatelessKieSession();
        Person p = new Person("Ternence");
        //开始根据Fact对象，匹配规则得到结果，如果符合条件规则引擎就会执行你定义的行为
        session.execute(p);
    }

    @Test
    public void testGenerateClass() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules/type_declare.drl"), ResourceType.DRL);

        KieBase kieBase = knowledgeBuilder.newKieBase();

        StatelessKieSession session = kieBase.newStatelessKieSession();

        session.execute(new Object());
    }
}
