# 安全控制框架——Shiro
## 5.5.3 如何实现安全控制框架
shiro依赖的版本原书上是1.2.3，由shiro的官方网站得知，最新最稳定版本是1.3.2，所以我自行改成了1.3.2版本

在写到`SecurityConfig`类的时候，发现`ConfigHelper`类并没有getString和getBoolean方法，而且翻看书本前后，貌似并没有提到要在`ConfigHelper`中添加该方法，所以我只能猜测是有这么个方法，用于获取配置文件中的某个字符串值，代码如下：

	/**
     * 获取 String 类型的属性值
     */
    public static String getString(String key) {
        return PropsUtil.getString(CONFIG_PROPS, key);
    }
	/**
     * 获取 boolean 类型的属性值
     */
    public static boolean getBoolean(String key) {
        return PropsUtil.getBoolean(CONFIG_PROPS, key);
    }

发现`ReflectionUtil`没有`newInstance(String className)`方法，书上没有提及，所以我查看黄勇的源码，发现根本没有调用这个方法，而是如下代码：

	public static SmartSecurity getSmartSecurity() {
        String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error("无法从 " + SecurityConstant.SMART_SECURITY + " 配置中找到对应的类", e);
        }
        SmartSecurity smartSecurity = null;
        if (cls != null) {
            try {
                smartSecurity = (SmartSecurity) cls.newInstance();
            } catch (Exception e) {
                logger.error(SmartSecurity.class.getSimpleName() + " 实例化异常", e);
            }
        }
        return smartSecurity;
    }
我决定用他源码里的代码替换书上的代码。

发现`SecurityConstant`书上有个常量是：`String CACHE = "kylin.plugin.security.cache"`但是`SecurityConfig`中调用的却是`CACHEABLE`，查看源码发现并没有CACHE，只有CACHEABLE，所以改成如下代码：

	String CACHEABLE = "smart.plugin.security.cacheable";

发现`DatabaseHelper`类没有`getDataSource`方法，查看源码，并不适用现阶段的代码，因为他的源码中出现了更复杂的调用，所以我决定研究自己现有的代码，像个办法补足这个方法，研究发现其实很简单，在`DatabaseHelper`类中添加如下代码即可：

	/**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

在写到`KylinJdbcRealm`类的时候，发现`new Md5CredentialsMatcher()`已经过时。查看shiro官网的API并百度查找相关资料得知用如下代码：

	super.setCredentialsMatcher(new HashedCredentialsMatcher("MD5"));

`new HashedCredentialsMatcher("MD5")`构造方法的参数中应该传入算法名称，而一般常用的算法名称有：  
1. MD2  
2. MD5  
3. SHA-1  
4. SHA-256  
5. SHA-384  
6. SHA-512 

上面MD5加密算法搞错了，书中代码`new Md5CredentialsMatcher()`其实是自己创建的的类。虽然搞错了，但shiro的加密算法这个过时的知识可以保留下来。

写到在CodecUtil类中添加`md5`方法的时候发现缺少`commons-codec`包，查源码发现在框架中依赖的

	<dependency>
        <groupId>commons-codec</groupId>
        <artifactId>commons-codec</artifactId>
        <version>1.11</version>
    </dependency>

在写到`SecurityHelper`类的时候，发现有个`AuthcException`异常类没有交代。查看源码，却是一个自定义的`LoginException`，所以决定直接使用`RuntimeException`。

写到自行添加其余Tag类的时候，发现subject并没有判断权限的方法，查找源码