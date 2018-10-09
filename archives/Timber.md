### 一.由来

Timber 是 JakeWharton的杰作，是一个极小的、扩展性很好的日志工具库，说是库，其实它只有一个类。所有的内容加起来不过644行。你用起来一定会爱上它。

Timber意为木材，木料。刚看到这个名字的时候，我一脸懵逼，这个名字怎么这么奇怪。然后去[github](https://github.com/JakeWharton/timber)上看了一下。起先这个库是在 JakeWharton 的一个App里使用的，后来他把这些代码复制出来，搞成的一个库。

Timber 把整个应用里用到的所有日志看成了一个森林，而把其中的一种日志看成了一棵树。在应用创建的时候将你需要的树种上`Timber.plant(Tree tree)`，就可以在设置tag之后使用了。这样类比一下，这个库叫做Timber也可以理解了，不管树还是森林，只有变成了木材我们才可以用来做家具盖房子。

### 二.主要元素

这个库的核心就是 树 和 森林。 

它把每一种log 都当成一棵树，我们每种一颗树就会，它就会把它加入到森林里。它的森林使用的ArrayList，每通过`Timber.plant(Tree tree)`加入一中log树，它就会add到ArrayList里。

它还有一个灵魂之树`TREE_OF_SOULS`,它是Timber的核心，不管我们打印什么日志(v,d,i,w,e)，都会通过它来执行。灵魂之树它会接管之前种下的所有的log树。它会通过for循环的方式依次调用各个log树的同名方法，这样就可以实现不同日志的记录。

### 三.使用

#### 一. DebugTree

上面说了它的每一种日志都是一颗树，如果我们需要输出不同类型的日志，那么我们就需要不同的树。

Timber 帮我们准好了一个 `DebugTree`,我们在设计自己的log树时也可以参考这个Tree,它限制了每一行log的长度为4000(logcat显示的大长度为4*1024，超出就无法显示)，tag的最大长度为 23 。并且对log的等级进行了判断，如果是 `ASSERT` 等级就会调用log.wtf()方法。

我们就先使用一下 DebugTree。

1. 在Application里把DebugTree 种上。

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
```

2. 设置TAG

   ```Java
   Timber.tag("Timber");	
   ```

   建议就在Application里设置一次。这里设置的tag，为所有log的tag，每调用一次就会改变一次。

3. 打印log

   ```java
   Timber.e("this is TimberActivity onCreate()");
   ```

   打印log的方法跟 Log类一样。

我们看一下打印的结果

	08-21 16:13:39.379 17828-17828/me.ricky.ospanalyze E/Timber: this is TimberActivity onCreate()

其实跟调用Log.e("Timber","this is TimberActivity onCreate()"); 结果的一样。

#### 二.自定义Tree

既然Timber涉及到了森林，只有一个DebuTree 可不叫做 森林。

Timber有个抽象的内部类 Timber.Tree。继承它，就可以根据log等级，tag和message等信息进行自定义输出了。

比如我想在树 Log.ERROR 这个等级的log保存到文件里并上报到服务器，以便于远程排错。那应该怎么办呢？且往下看。

首先我们只过滤Error这个级别的log，然后把它SDCard上，最后上传服务器。

##### 过滤Log的等级

抽象类Tree里有个 `boolean isLoggable(@Nullable String tag, int priority)`方法，它默认返回true，在这里我们就可以将Error等级的log留下，其它的就过滤掉。如下：

```java
    @Override
    protected boolean isLoggable(@Nullable String tag, int priority) {
        if (priority != Log.ERROR) {
            return false;
        }
        return super.isLoggable(tag, priority);
    }
```



