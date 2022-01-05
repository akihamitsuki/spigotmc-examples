package com.example.bindbook;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.BookMeta.Generation;

public class BookCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (sender instanceof Player) {
      Player player = (Player) sender;
      player.getInventory().addItem(bindBook());
    }

    return false;
  }

  /**
   * 記入済みの本を作成する
   *
   * @return 作成した本のアイテムデータ
   */
  private ItemStack bindBook() {
    // 本のアイテムデータを作成
    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
    // 本のメタデータを取得
    BookMeta meta = (BookMeta) book.getItemMeta();
    // メタデータの設定
    // 題名
    meta.setTitle(ChatColor.RED + "吾輩は猫である");
    // 著者
    meta.setAuthor(ChatColor.YELLOW + "夏目漱石");
    // 複製状況
    meta.setGeneration(Generation.COPY_OF_ORIGINAL);
    // ページを追加する
    meta.addPage(
      // \nで改行
      "吾輩《わがはい》は猫である。名前はまだ無い。\n"
      + "どこで生れたかとんと見当《けんとう》がつかぬ。"
      + "何でも薄暗いじめじめした所で"
      + ChatColor.DARK_RED + "ニャーニャー"
      + ChatColor.RESET + "泣いていた事だけは記憶している。"
    );
    // コンマで区切り、別の引数にすると別ページになる
    meta.addPage(
      "吾輩はここで始めて人間というものを見た。しかもあとで聞くとそれは書生という人間中で一番｜獰悪《どうあく》な種族であったそうだ。この書生というのは時々我々を捕《つかま》えて煮《に》て食うという話である。",
      "しかしその当時は何という考もなかったから別段恐しいとも思わなかった。ただ彼の掌《てのひら》に載せられてスーと持ち上げられた時何だかフワフワした感じがあったばかりである。掌の上で少し落ちついて書生の顔を見たのがいわゆる人間というものの見始《みはじめ》であろう。"
    );
    // 1ページあたり最大で半角256文字分。全角だと168文字分くらい
    // 長すぎると途中で切れる(自動で次のページにいかない)
    meta.addPage("この時妙なものだと思った感じが今でも残っている。第一毛をもって装飾されべきはずの顔がつるつるしてまるで薬缶《やかん》だ。その後《ご》猫にもだいぶ逢《あ》ったがこんな片輪《かたわ》には一度も出会《でく》わした事がない。のみならず顔の真中があまりに突起している。そうしてその穴の中から時々ぷうぷうと煙《けむり》を吹く。どうも咽《む》せぽくて実に弱った。これが人間の飲む煙草《たばこ》というものである事はようやくこの頃知った。");
    // メタデータを本に設定する
    book.setItemMeta(meta);

    return book;
  }
}
