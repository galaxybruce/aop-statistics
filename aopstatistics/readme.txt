1、aspectj已经打包成库，在repo目录下。源码在buildSrc目录下
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
3、在需要用到切面编程的module中，是需要切面的每个module， 不是只是在app下添加就可以，必须做添加如下代码
    apply plugin: com.kidswant.gradle.android.AndroidAspectJPlugin
    compile project(':aopstatistics')

4、setting.gradle中引入aopstatistics module
    include ':aopstatistics'
    project(':aopstatistics').projectDir = new File('../../library/aopstatistics/trunk')

5、配置数据:res/raw/reportpoint.proterties文件中配置，在合适的地方调用reportpoint.proterties方法加载


切入点规则：
1、事件名都以KisListener结尾，如果该事件需要传参，添加String param参数
2、页面事件都在KidBaseActivity和KidBaseFragment中，切入方法是reportPagePoint