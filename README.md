# MVPApplication
MVP模式纯净版：

            1. 没有嵌入任何框架,可以自由加入框架：
					网络请求(Okhttp,Nohttp,Volley,Retrofit)
					图片加载(Glide,imageloader,Picasso,Fresco)
					数据库(Ormlite,GreenDao,Realm)
					依赖注入(Butterknife,Dragger2);
            2. View,Presenter,Model根据包名分层, modelinterface包是Model向Presenter通讯接口,viewinterface包是Presenter向View层通讯接口;
            3. Presenter对象中的mModel和View对象的Presenter对象都是通过Class反射得到的, 所以要确保getPresenterClass,getModelClass返回的Class含有公有的构造方法;
            4. 获取网络数据可以写在Model里面，通过modelinterface回调给Presenter,然后通过viewinterface更新View对象;
            5. 本案例对BaseFragment和BaseActivity进行了测试, 只是没有网络API请求,所以Model层直接返回的测试数据;
            6. 为了MVP模式更加完善,后期准备封装Retrofit2+Dragger2+rxjava+Butterknife+GreenDao+ 常用工具类进来, 欢迎大家star!!
	     7. 如果项目中有写的不好的地方,还望大家多多指教,谢谢！ 联系方式 QQ:295476281。
