# イベントを検知する

ワールド内で「何かをしたとき」を検知して処理を加えることができます。

今回はサンプルとして「プレイヤーが歩いたとき(イベント)」に足元のブロックを赤の羊毛に変える、という処理を加えています。

1. イベント内容のクラスで作成する(`PlayerListener.java`)
2. `JavaPlugin`(`Main.java`の`this`)を使って、イベント検知(リスナー)を登録する。

その他のイベントは次のページを参照。

[https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/class-use/Event.html](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/class-use/Event.html)
