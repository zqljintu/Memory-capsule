# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
 -dontoptimize
 -dontpreverify
 -optimizationpasses 5
 -dontusemixedcaseclassnames
 -dontskipnonpubliclibraryclasses
 -verbose
 -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
 -renamesourcefileattribute SourceFile
 -keepattributes SourceFile,LineNumberTable

 -keepattributes *Annotation*, InnerClasses, Signature, SourceFile, LineNumberTable, Exceptions

 -dump class_files.txt
 -printseeds seeds.txt
 -printusage unused.txt
 -printmapping mapping.txt

 -ignorewarnings

 # 对R文件下的所有类及其方法，都不能被混淆
 -keepclassmembers class **.R$* {
    *;
 }

 #自定义控件
 -keep public class * extends android.view.View
 -keep public class * extends android.widget.RelativeLayout
 -keep public class * extends android.widget.FrameLayout
 -keep public class * extends android.widget.LinearLayout
 -keep public class * extends ViewPager
 -keep public class * extends ScrollView
 -keep public class * extends Scroller
 -keep public class * extends ProgressBar

 #android组件
 -keep public class * extends android.app.AppCompatActivity
 -keep public class * extends android.app.Application
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider
 -keep public class * extends android.app.backup.BackupAgentHelper
 -keep public class * extends android.preference.Preference
 -keep public class com.android.vending.licensing.ILicensingService

 -keep class com.google.android.material.** {*;}
 -keep class androidx.** {*;}
 -keep public class * extends androidx.**
 -keep interface androidx.** {*;}
 -dontwarn com.google.android.material.**
 -dontnote com.google.android.material.**
 -dontwarn androidx.**

 # 保留Activity中的方法参数是view的方法，
 # 从而我们在layout里面编写onClick就不会影响
 -keepclassmembers class * extends android.app.Activity {
     public void * (android.view.View);
 }

 # 枚举类不能被混淆
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 # 保留Parcelable序列化的类不能被混淆
 -keep class * implements android.os.Parcelable{
     public static final android.os.Parcelable$Creator *;
 }

 # 保留Serializable 序列化的类不被混淆
 -keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
 }

 #native
 -keepclasseswithmembernames class * {
     native <methods>;
 }

 #support
 -dontwarn android.support.**
 -keep class android.support.v4.** { *; }
 -keep class android.support.v7.** { *; }

 -keep class android.support.design.** { *; }
 -keep interface android.support.design.** { *; }
 -keep public class android.support.design.R$* { *; }

 #fresco
 -dontwarn com.facebook.**
 -keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

 -keep @com.facebook.common.internal.DoNotStrip class *

 ############### facebook混淆  ###############
 -keep class com.facebook.ads.** {
     <fields>;
     <methods>;
 }

 -keepclassmembers class * {
     @com.facebook.common.internal.DoNotStrip *;
 }

 -dontwarn okio.**
 -dontwarn com.squareup.okhttp.**
 -dontwarn javax.annotation.**
 -dontwarn com.android.volley.toolbox.**

 #rxjava
 -dontnote
 -dontwarn sun.misc.**

 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
 }

 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }

 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

 # gson
 -keepattributes Signature
 -keepattributes *Annotation*
 -dontwarn sun.misc.Unsafe
 -keep class sun.misc.Unsafe { *; }
 -keep class com.google.gson.stream.** { *; }

 # Application classes that will be serialized/deserialized over Gson
 -keep class com.google.gson.examples.android.model.** { *; }

 #okhttp
 -dontwarn okhttp3.**
 -dontwarn okio.**
 -keep class okhttp3.** { *; }
 -keep interface okhttp3.** { *; }

 -keep class com.squareup.sqlbrite.**{*;}


 #otto
 -keepclassmembers class ** {
     @com.squareup.otto.Subscribe public *;
     @com.squareup.otto.Produce public *;
 }

 #facebook
 -dontwarn com.facebook.**
 -keep class com.facebook.** {
    *;
 }

 #gms
 -dontwarn com.google.android.gms.**
 -keep class com.google.android.gms.** { *; }
 -keep class com.google.firebase.** { *; }
 -keep class * extends java.util.ListResourceBundle {
     protected Object[][] getContents();
 }

 -keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
     public static final *** NULL;
 }

 -keepnames @com.google.android.gms.common.annotation.KeepName class *
 -keepclassmembernames class * {
     @com.google.android.gms.common.annotation.KeepName *;
 }

 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
 }

 #squareup
 -dontwarn com.squareup.**
 -keep class com.squareup.**{*;}

 # butterknife7.0
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }
 -keepclasseswithmembernames class * {
    @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
  @butterknife.* <methods>;
 }

 # glide
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 #retrofit
 -dontwarn retrofit.**
 -keep class retrofit.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions
 -dontwarn com.squareup.okhttp.**

 -keepclassmembers,allowobfuscation interface * {
     @retrofit.http.** <methods>;
 }

 -keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
 }

 -keepclasseswithmembers class * {
     @retrofit2.http.* <methods>;
 }

 #Umeng
 -keepclassmembers class * {
    public <init> (org.json.JSONObject);
 }

 #google billing
 -keep class com.android.vending.billing.**

 #appsflyer
 -dontwarn com.appsflyer.**
 -keep class com.appsflyer.** { *; }

 -keep class Security.**{
     *;
 }

 #EventBus
 -keepclassmembers class ** {
     @org.greenrobot.eventbus.Subscribe <methods>;
 }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }

 # Only required if you use AsyncExecutor
 -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
     <init>(Java.lang.Throwable);
 }

 #appsflyer
 -dontwarn com.appsflyer.**
 -keep class com.appsflyer.** { *; }
 #Bugly
 -dontwarn com.tencent.bugly.**
 -keep public class com.tencent.bugly.**{*;}
 #aidl
 -keep class android.content.pm.IPackageDataObserver { *; }
 -keep class android.content.pm.IPackageStatsObserver { *; }
 -keep class android.content.pm.PackageStats { *; }

 -keep class com.pingstart.adsdk.adapter.**{ *; }
 -keep class com.pingstart.mobileads.**{ *; }

 #X5
 # Addidional for x5.sdk classes for apps
 -keep class com.tencent.smtt.export.external.**{*;}
 -keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {*;}
 -keep class com.tencent.smtt.sdk.CacheManager {public *;}
 -keep class com.tencent.smtt.sdk.CookieManager {public *;}
 -keep class com.tencent.smtt.sdk.WebHistoryItem {public *;}
 -keep class com.tencent.smtt.sdk.WebViewDatabase {public *;}
 -keep class com.tencent.smtt.sdk.WebBackForwardList {public *;}
 -keep public class com.tencent.smtt.sdk.WebView {public <fields>;public <methods>;}
 -keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
 	public static final <fields>;
 	public java.lang.String getExtra();
 	public int getType();
 }
 -keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {public <methods>;}
 -keep public class com.tencent.smtt.sdk.WebView$PictureListener {public <fields>;public <methods>;}
 -keepattributes InnerClasses
 -keep public enum com.tencent.smtt.sdk.WebSettings$** {*;}
 -keep public enum com.tencent.smtt.sdk.QbSdk$** {*;}
 -keep public class com.tencent.smtt.sdk.WebSettings {public *;}

 -keepattributes Signature
 -keep public class com.tencent.smtt.sdk.ValueCallback {public <fields>;public <methods>;}
 -keep public class com.tencent.smtt.sdk.WebViewClient {public <fields>;public <methods>;}
 -keep public class com.tencent.smtt.sdk.DownloadListener {public <fields>;public <methods>;}
 -keep public class com.tencent.smtt.sdk.WebChromeClient {public <fields>;public <methods>;}
 -keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {public <fields>;public <methods>;}
 -keep class com.tencent.smtt.sdk.SystemWebChromeClient{public *;}
 # 1. extension interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.extension.interfaces.* {public protected *;}
 # 2. interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.interfaces.* {public protected *;}
 -keep public class com.tencent.smtt.sdk.WebViewCallbackClient {public protected *;}
 -keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.WebIconDatabase {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.WebStorage {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.DownloadListener {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.QbSdk {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.Tbs* {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.utils.LogFileUtils {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.utils.TbsLog {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.utils.TbsLogClient {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
 	public <fields>;
 	public <methods>;
 }
 # Added for game demos
 -keep public class com.tencent.smtt.sdk.TBSGamePlayer {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.utils.Apn {
 	public <fields>;
 	public <methods>;
 }
 # end
 -keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
 	public <fields>;
 	public <methods>;
 }
 -keep class MTT.ThirdAppInfoNew {*;}
 -keep class com.tencent.mtt.MttTraceEvent {*;}
 # GameChangeActivity related
 -keep public class com.tencent.smtt.gamesdk.* {public protected *;}
 -keep public class com.tencent.smtt.sdk.TBSGameBooter {public <fields>;public <methods>;}
 -keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {public protected *;}
 -keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {public protected *;}
 -keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {public *;}
 #
 -dontwarn com.newborntown.android.weatherviewlibrary.**
 -keep class com.newborntown.android.weatherviewlibrary.** { *;}
 -dontwarn com.newborntown.android.weatherlibrary.**
 -keep class com.newborntown.android.weatherlibrary.** { *;}

 -keep class cn.bingoogolapple.bgabanner.BGAViewPager { *; }

 # AdMob Mediation
 -keep class com.google.ads.mediation.admob.AdMobAdapter {
     *;
 }

 -keep class com.google.ads.mediation.AdUrlAdapter {
     *;
 }


 #for gaid
 -keep class **.AdvertisingIdClient$** { *; }

 #for js and webview interface
 -keepclassmembers class * {
      @android.webkit.JavascriptInterface <methods>;
 }

 -keep @com.qihoo.SdkProtected.sec.Keep class **{*;}

 -keep,allowobfuscation @interface com.qihoo.SdkProtected.sec.Keep

 #广告混淆去除log
 -keep class com.pingstart.**{ *; }
 -assumenosideeffects class android.util.Log {
      public static boolean isLoggable(java.lang.String, int);
      public static int v(...);
      public static int i(...);
      public static int w(...);
      public static int d(...);
      public static int e(...);
 }
 -assumenosideeffects class com.newborntown.android.lib.ads.DebugLogger {
      public static *** v(...);
      public static *** i(...);
      public static *** w(...);
      public static *** d(...);
      public static *** e(...);
 }

 -keepattributes SourceFile,LineNumberTable
 -keep class com.inmobi.** { *; }
 -dontwarn com.inmobi.**
 -keep public class com.google.android.gms.**
 -dontwarn com.google.android.gms.**
 -dontwarn com.squareup.picasso.**
 -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
 -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}

 #skip the Picasso library classes
 -keep class com.squareup.picasso.** {*;}
 -dontwarn com.squareup.picasso.**
 -dontwarn com.squareup.okhttp.**

 #skip Moat classes
 -keep class com.moat.** {*;}
 -dontwarn com.moat.**

 #skip AVID classes
 -keep class com.integralads.avid.library.** {*;}

 # Vungle
 -keep class com.vungle.warren.** { *; }
 -dontwarn com.vungle.warren.error.VungleError$ErrorCode

 # Moat SDK
 -keep class com.moat.** { *; }
 -dontwarn com.moat.**

 # Okio
 -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

 # Retrofit
 -dontwarn okio.**
 -dontwarn retrofit2.Platform$Java8

 # Gson
 -keepattributes Signature
 -keepattributes *Annotation*
 -dontwarn sun.misc.**
 -keep class com.google.gson.examples.android.model.** { *; }
 -keep class * implements com.google.gson.TypeAdapterFactory
 -keep class * implements com.google.gson.JsonSerializer
 -keep class * implements com.google.gson.JsonDeserializer

 # Google Android Advertising ID
 -keep class com.google.android.gms.internal.** { *; }
 -dontwarn com.google.android.gms.ads.identifier.**


 -keep class com.bytedance.sdk.openadsdk.** { *; }
 -keep class com.androidquery.callback.** {*;}
 -keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}
 -keep class com.ss.sys.ces.* {*;}

 -keep class com.totoro.admodule.**{*;}
 -keepclassmembers class ** {
     @com.totoro.admodule.life.LifeCall <methods>;
 }
 -keep enum com.totoro.admodule.life.LifeState { *; }

 -keep class * extends java.lang.annotation.Annotation { *; }
 -keep interface * extends java.lang.annotation.Annotation { *; }

 -keep class com.qq.e.**{*;}

 -keep class com.qq.e.** {
      public protected *;
 }
 -keep class android.support.v4.**{
      public *;
 }
 -keep class android.support.v7.**{
      public *;
 }

 -keep class MTT.ThirdAppInfoNew {
      *;
 }
 -keep class com.tencent.** {
      *;
 }

 -keep class com.umeng.** {*;}
 -keepclassmembers class * {
    public <init> (org.json.JSONObject);
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keep class **.R$*{*;}

 -dontoptimize
 -dontpreverify

 -dontwarn cn.jpush.**
 -keep class cn.jpush.** { *; }
 -keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

 -dontwarn cn.jiguang.**
 -keep class cn.jiguang.** { *; }

 #==================gson && protobuf==========================
 -dontwarn com.google.**
 -keep class com.google.gson.** {*;}
 -keep class com.google.protobuf.** {*;}


 -dontwarn com.baidu.**
 -keep class com.baidu.**{*; }

 -dontwarn com.umeng.**
 -dontwarn com.taobao.**
 -dontwarn anet.channel.**
 -dontwarn anetwork.channel.**
 -dontwarn org.android.**
 -dontwarn org.apache.thrift.**
 -dontwarn com.xiaomi.**
 -dontwarn com.huawei.**
 -dontwarn com.meizu.**

 -keepattributes *Annotation*

 -keep class com.taobao.** {*;}
 -keep class org.android.** {*;}
 -keep class anet.channel.** {*;}
 -keep class com.umeng.** {*;}
 -keep class com.xiaomi.** {*;}
 -keep class com.huawei.** {*;}
 -keep class com.meizu.** {*;}
 -keep class org.apache.thrift.** {*;}

 -keep class com.alibaba.sdk.android.**{*;}
 -keep class com.ut.**{*;}
 -keep class com.ta.**{*;}

 -keep public class **.R$*{
    public static final int *;
 }

 -keep class com.uniplay.adsdk.**
 -keep class com.joomob.**
 -keep class * implements android.os.Parcelable {
   public static final android.os.Parcelable$Creator *;
 }
 -keepattributes Annotation
 -keepattributes JavascriptInterface
 -keepclassmembers class * {
 @android.webkit.JavascriptInterface <methods>;
 }
 -keepclassmembers public class com.uniplay.adsdk.JavaScriptInterface{
 <fields>;
 <methods>;
 public *;
 private *;
 }

 -keep class com.tencent.mm.opensdk.** {
     *;
 }

 -keep class com.tencent.wxop.** {
     *;
 }

 -keep class com.tencent.mm.sdk.** {
     *;
 }


 -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
 public static java.lang.String TABLENAME;
 }
 -keep class **$Properties
 -dontwarn org.greenrobot.greendao.database.**
 -dontwarn rx.**

 #ARouter
 -keep public class com.alibaba.android.arouter.routes.**{*;}
 -keep public class com.alibaba.android.arouter.facade.**{*;}
 -keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

 # 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
 -keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

 # 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
 # -keep class * implements com.alibaba.android.arouter.facade.template.IProvider






 -dontshrink
 -dontoptimize
 -dontwarn com.google.android.maps.**
 -dontwarn android.webkit.WebView
 -dontwarn com.umeng.**
 -dontwarn com.tencent.weibo.sdk.**
 -dontwarn com.facebook.**
 -keep public class javax.**
 -keep public class android.webkit.**
 -dontwarn android.support.v4.**
 -keep enum com.facebook.**
 -keepattributes Exceptions,InnerClasses,Signature
 -keepattributes *Annotation*
 -keepattributes SourceFile,LineNumberTable

 -keep public interface com.facebook.**
 -keep public interface com.tencent.**
 -keep public interface com.umeng.socialize.**
 -keep public interface com.umeng.socialize.sensor.**
 -keep public interface com.umeng.scrshot.**

 -keep public class com.umeng.socialize.* {*;}


 -keep class com.facebook.**
 -keep class com.facebook.** { *; }
 -keep class com.umeng.scrshot.**
 -keep public class com.tencent.** {*;}
 -keep class com.umeng.socialize.sensor.**
 -keep class com.umeng.socialize.handler.**
 -keep class com.umeng.socialize.handler.*
 -keep class com.umeng.weixin.handler.**
 -keep class com.umeng.weixin.handler.*
 -keep class com.umeng.qq.handler.**
 -keep class com.umeng.qq.handler.*
 -keep class UMMoreHandler{*;}
 -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
 -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
 -keep class im.yixin.sdk.api.YXMessage {*;}
 -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 -keep class com.tencent.mm.opensdk.** {
    *;
 }
 -keep class com.tencent.wxop.** {
    *;
 }
 -keep class com.tencent.mm.sdk.** {
    *;
 }

 -keep class com.twitter.** { *; }

 -keep class com.tencent.** {*;}
 -dontwarn com.tencent.**
 -keep class com.kakao.** {*;}
 -dontwarn com.kakao.**
 -keep public class com.umeng.com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep public class com.linkedin.android.mobilesdk.R$*{
     public static final int *;
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keep class com.tencent.open.TDialog$*
 -keep class com.tencent.open.TDialog$* {*;}
 -keep class com.tencent.open.PKDialog
 -keep class com.tencent.open.PKDialog {*;}
 -keep class com.tencent.open.PKDialog$*
 -keep class com.tencent.open.PKDialog$* {*;}
 -keep class com.umeng.socialize.impl.ImageImpl {*;}
 -keep class com.sina.** {*;}
 -dontwarn com.sina.**
 -keep class  com.alipay.share.sdk.** {
    *;
 }

 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
 }

 -keep class com.linkedin.** { *; }
 -keep class com.android.dingtalk.share.ddsharemodule.** { *; }
 -keepattributes Signature

 -dontwarn com.androidquery.**
 -keep class com.androidquery.** { *;}

 -dontwarn tv.danmaku.**
 -keep class tv.danmaku.** { *;}

 -dontwarn androidx.**

 # 如果使用了tbs版本的sdk需要进行以下配置
 -keep class com.tencent.smtt.** { *; }
 -dontwarn dalvik.**
 -dontwarn com.tencent.smtt.**

 # 如果使用了微信OpenSDK，需要添加如下配置
 -keep class com.tencent.mm.opensdk.** {
     *;
 }

 -keep class com.tencent.wxop.** {
     *;
 }

 -keep class com.tencent.mm.sdk.** {
     *;
 }

 # 如果接入了Bugly，需要添加如下配置
 -dontwarn com.tencent.bugly.**
 -keep public class com.tencent.bugly.**{*;}

 # wechat
 -keep class com.tencent.mm.opensdk.** {*;}
 -keep class com.tencent.wxop.** {*;}
 -keep class com.tencent.mm.sdk.** {*;}


 -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
 public static java.lang.String TABLENAME;
 }
 -keep class **$Properties
 -dontwarn org.greenrobot.greendao.database.**
 -dontwarn rx.**
 #svga混淆
 -keep class com.squareup.wire.** { *; }
 -keep class com.opensource.svgaplayer.proto.** { *; }

 #  ---------------------------------litepal---------------------------------
 -dontwarn org.litepal.**
 -keep class org.litepal.** {*; }

 -keep class * extends org.litepal.crud.DataSupport {
     *;
 }

 -keep class * extends org.litepal.crud.LitePalSupport {
     *;
 }

 -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
 public static java.lang.String TABLENAME;
 }
 -keep class **$Properties
 -dontwarn org.greenrobot.greendao.database.**
 -dontwarn rx.**

 #ARouter
 -keep public class com.alibaba.android.arouter.routes.**{*;}
 -keep public class com.alibaba.android.arouter.facade.**{*;}
 -keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

 # 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
 -keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

 -dontwarn com.tencent.bugly.**
 -keep public class com.tencent.bugly.**{*;}

 #BRVAH 打包混淆
 -keep class com.chad.library.adapter.** {
 *;
 }
 -keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
 -keep public class * extends com.chad.library.adapter.base.BaseViewHolder
 -keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
      <init>(...);
 }
 #ShareSdk 打包混淆
 -keep class cn.sharesdk.**{*;}
 -keep class com.sina.**{*;}
 -keep class com.mob.**{*;}
 -keep class com.bytedance.**{*;}
 -dontwarn cn.sharesdk.**
 -dontwarn com.sina.**
 -dontwarn com.mob.**

 ######################################################豹趣小游戏
 # 需要维持拓展库中的注释
 -keep class com.cmgame.x5fit.X5CmGameSdk{*;}
 -keep class com.cmgame.x5fit.X5WebViewModule{*;}

 # 需要保持游戏SDK的Bean类不被混淆
 -keep class com.cmcm.cmgame.gamedata.bean.* {*;}
 -keep class com.cmcm.cmgame.bean.* {*;}
 -keep class com.cmcm.cmgame.httpengine.bean.* {*;}
 ######################################################豹趣小游戏


 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 -dontwarn com.lxj.xpopup.widget.**
 -keep class com.lxj.xpopup.widget.**{*;}

# -keep public class com.solo.ad.AdLog{*;}


 # for DexGuard only
 #-keepresourcexmlelements manifest/application/meta-data@value=GlideModule