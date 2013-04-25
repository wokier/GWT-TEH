GWT-TEH
=======

TEH stands for __T__oString __E__quals __H__ashCode

gwt-teh is the gwt-compatible version of [TEH](https://github.com/wokier/TEH).

## Usage

 - Just extends GWTEHObject.

 - Define this dependency

```
<dependency>
			<groupId>com.github.wokier</groupId>
			<artifactId>gwt-teh</artifactId>
			<version>0.7</version>
			<classifier>teh</classifier>
</dependency>
```

 - Add an inherits 'teh.TEH' in your module .gwt.xml

 - Don't forget to define teh.gwt.server.GWTEHInitServlet in your web.wml with a low loadOnStartup parameter.

- - -
[Demo](http://gwt-teh-demo.appspot.com/)

[Demo Source](https://github.com/wokier/GWT-TEH-DEMO)

## Release Notes
0.7 2013-04-24 GWT 2.5.0 Compatibility. Maven Central Release. Uses TEH 0.7
