# Memory-capsule
This is a sample memorandum,but I named it Memory-capsule.//这只是一个简单的备忘录程序，只是我把它命名为记忆胶囊。

项目简介（适配Android5.0--8.0。）
====

这只是一个简单的备忘录程序，给她取了一个名字叫“记忆胶囊”，来记录我的一些想法，回忆，行程等等。同时通过写个App,开阔了眼界，丰富了知识，很高兴能把它开源出来，为开源世界做出自己的一点小贡献。

    解决了一些问题：
    1，为没有备忘文件下的界面添加了背景图片。
    2，解决了从列表文件删除后，没有及时再主界面显示更新这个问题。
    
    更新版本说明（v1.10）
    1，添加文本分享模块，调用手机系统分享模块实现，能分享到多个软件。（注：微信朋友圈，QQ空间不能被分享）
    2，添加动画属性，对详情界面添加了两个动画效果，在以后的更新中将添加更多的动画效果。
    3，更改了被多人吐槽的图标，扁平化效果，借鉴了网上的一些素材。
    
    更新版本说明（v1.03）
    1，数据分析模块添加了一个新的项目，完成了饼状图和柱状图的双设计！
    2，重新设计了密码输入界面，使密码输入更加安全，并且统一了界面。
    3，完成了对删除之后界面跳转的优化，让界面更加顺滑。
    4，修复了一些测试中的小BUG。（并没有杀程序员祭天！）

    更新版本说明（v1.02）
    1，添加了数据分析模块，用户可以查看创建类型数据分布。
    2，完成上一版本中主界面添加入私密文件夹功能。
    3，优化了相应的界面设计，使界面整体风格更加统一协调。

    更新版本说明（V1.01）
    1，添加了密码模块，用户可以自由添加个人备忘录于密码文件夹中。
    2，密码文件夹采用了另一个数据库文件，与普通备忘录文件隔离开，加强了安全可靠性。
    3，编辑界面添加了一个switch button，用来控制备忘文件的类型。
    4，主界面添加入密码中在下一版本中实现。
    5，秘密文件夹中备忘文件不能被编辑，只能查看与删除。
    
 
    此版本为第一个版本(V1.00)，其中有些功能并未完善，具体如下：
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
    switchbutton库：compile 'com.kyleduo.switchbutton:library:2.0.0'
    chart图表开源库：compile 'com.github.lecho:hellocharts-library:1.5.8@aar'
    等。
    动画开源库：implementation 'com.android.support:support-compat:26.1.0'
               implementation 'com.daimajia.easing:library:2.0@aar'
               implementation 'com.daimajia.androidanimations:library:2.3@aar'
    在此，感谢各位大神的轮子。
    
 程序采用了mvp架构的设计，新手所写，还有诸多不足之处，如果喜欢，欢迎给个Star,若有什么问题，欢迎issue。
 联系请关注知乎账号：番茄炒蛋不要蛋
 
    
