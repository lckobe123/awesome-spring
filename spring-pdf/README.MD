#注意事项

在使用flying转换html为pdf的过程中，如果html中有中文，
将显示不出来，为了能够显示中文，可以使用能够显示中文的字体
，然后将字体添加到ITextRenderer的字体解析器中(ITextFontResolver),
这里我们使用的是 `宋体 simsun.ttc` 相关的字体文件可以在
C:\Windows\Fonts目录下找到，
最后在增加一个如下的样式就可以解决中文显示的问题了
```$xslt
<style>
   body {
       font-family: SimSun;
   }
</style>
```
