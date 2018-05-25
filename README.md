# Memory-capsule
This is a sample memorandum,but I named it Memory-capsule.//这只是一个简单的备忘录程序，只是我把它命名为记忆胶囊。

项目简介（适配Android5.0--8.0。）
====

这只是一个简单的备忘录程序，给她取了一个名字叫“记忆胶囊”，来记录我的一些想法，回忆，行程等等。同时通过写个App,开阔了眼界，丰富了知识，很高兴能把它开源出来，为开源世界做出自己的一点小贡献。

 
    此版本为第一个版本，其中有些功能并未完善，具体如下：
    1，密码模块已经完善，有了创建密码和密保，修改密码和密保，忘记密码等功能，但是"我的秘密"这个模块并没有添加进去，还处于空白区。
    2，有些菜单界面中的隐藏功能并未实现，在下一版本中更新。
    3，修改第一个Item内容时，第一个Item显示Bug，需要退出重启后才能显示成功。
    4，通过拍摄创建时，主界面中Item图片无法显示。
    5，列表中的下拉菜单背景色无法随着toolbar颜色改变。

相应界面设计如下：
![](https://github.com/zqljintu/Memory-capsule/blob/master/ReadMe_Image/0001.jpg)

其中使用了许多开源库：

    图片加载库：Glide
    数据库：GreenDao
    下拉菜单：nice-spinner
    输入框：materialedittext
    图片显示，拍摄库：imgsel
    沉浸式状态栏库：statusbarutil
    floatingActionButton开源库：'com.getbase:floatingactionbutton:1.10.1'
    floatingbutton库：compile 'com.github.joaquimley:faboptions:1.2.0'
    标签库：'me.gujun.android.taggroup:library:1.4@aar'
    等。
    在此，感谢各位大神的轮子。
    
 程序采用了mvp架构的设计，新手所写，还有许多不足之处，如果喜欢，欢迎给个Star,若有什么问题，欢迎issue。
 
    
