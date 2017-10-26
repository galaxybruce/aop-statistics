1、aspectj已经打包成库，在repo目录下。源码在buildSrc目录下。使用如下命令
    gradlew -p aopstatistics/buildSrc/ clean build uploadArchives
	或者在其他项目目录下用绝对路径gradlew -p D:/android-open-project/aop-statistics/aopstatistics/buildSrc/ clean build uploadArchives

2、在项目根目录下的build.gradle中添加插件库
    buildscript {
        repositories {
            maven {
                url uri('file:../../library/aopstatistics/trunk/repo')
            }
            jcenter()
        }

        dependencies {
            classpath  'com.kidswant.aop:gradle:1.0.0'
        }
    }
3、在需要用到切面编程的module中，是需要切面的每个module，必须引入这个插件以及java代码；
    apply plugin: com.kidswant.gradle.android.AndroidAspectJPlugin
    compile project(':aopstatistics')
     初始化配置的module，如app中如果没用到切面，不需要引入插件，只需要引入java代码部分
     compile project(':aopstatistics')

4、setting.gradle中引入aopstatistics module
    include ':aopstatistics'
    project(':aopstatistics').projectDir = new File('../../library/aopstatistics/trunk')

5、配置数据:res/raw/reportpoint.proterties文件中配置，在合适的地方调用reportpoint.proterties方法加载
   调用AopStatistics.loadProperties方法加载配置。
	com.xxx.xxx.MyActivity&flagXXX=004:010201:10007
	com.xxx.xxx.MyActivityFragment&flagXXX=004:010201:10008
	com.xxx.xxx.MyActivity.doActivityKisClick=004::10009
	com.xxx.xxx.MyActivity.doActivityParamKisClick=004::10010

6、调用方式
       new AopStatistics.Builder().addAopStatistics(new IAopStatistics() {
            @Override
            public void reportEvent(String bussinessType, String eventId, String repoParam) {
                Log.i("aaaaaaaa", "reportEvent: " + bussinessType + "-" + eventId + "-" + repoParam);
            }

            @Override
            public void reportPageOnResume(String bussinessType, String pageId, String eventId, String repoParam) {
                Log.i("aaaaaaaa", "reportPageOnResume: " + bussinessType + "-" + pageId + "-" + eventId + "-" + repoParam);
            }

            @Override
            public void reportPageOnPause(String bussinessType, String pageId, String eventId, String repoParam) {
                Log.i("aaaaaaaa", "reportPageOnPause: " + bussinessType + "-" + pageId  + "-" + eventId + "-" + repoParam);
            }
        }).build().loadProperties(this);



切入点规则：
1、事件名都以KisListener结尾，如果该事件需要传参，添加String param参数
2、页面事件都在KidBaseActivity和KidBaseFragment中，切入方法是reportPagePoint