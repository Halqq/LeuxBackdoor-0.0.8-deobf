package me.obsidianbreaker.leux.client.modules.player;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketBlockBreakAnim;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Notifications extends Module {
  @EventHandler
  public Listener packetReceiveListener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public int numTicks = 0;
  
  public Setting visualrange = create("VisualRange", "VisualRange", false);
  
  public List people;
  
  public HashMap tpdPlayers = new HashMap<>();
  
  public Setting breakwarner = create("BreakWarner", "BreakWarner", true);
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public Set str = Collections.newSetFromMap(new WeakHashMap<>());
  
  public HashMap knownPlayers = new HashMap<>();
  
  public Setting coordexploit = create("CoordExploit", "CoordExploit", false);
  
  public int numForgetTicks = 0;
  
  public Setting strength = create("Strength", "Strength", false);
  
  public Setting distanceToDetect = create("Max Break Distance", "WarnerMaxDistance", 2, 1, 5);
  
  public Setting chatDelay = create("Chat Delay", "WarnerChatDelay", 18, 14, 25);
  
  public void enable() {
    (give up)null;
    this.people = new ArrayList();
  }
  
  public void update() {
    // Byte code:
    //   0: goto -> 3
    //   3: aconst_null
    //   4: checkcast give up
    //   7: pop
    //   8: aload_0
    //   9: getfield strength : Lme/obsidianbreaker/leux/client/guiscreen/settings/Setting;
    //   12: iconst_1
    //   13: invokevirtual get_value : (Z)Z
    //   16: ifeq -> 223
    //   19: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   22: getfield field_71441_e : Lnet/minecraft/client/multiplayer/WorldClient;
    //   25: getfield field_73010_i : Ljava/util/List;
    //   28: invokeinterface iterator : ()Ljava/util/Iterator;
    //   33: astore_1
    //   34: aload_1
    //   35: invokeinterface hasNext : ()Z
    //   40: ifeq -> 223
    //   43: aload_1
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: checkcast net/minecraft/entity/player/EntityPlayer
    //   52: astore_2
    //   53: aload_2
    //   54: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   57: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   60: invokevirtual equals : (Ljava/lang/Object;)Z
    //   63: ifeq -> 69
    //   66: goto -> 34
    //   69: aload_2
    //   70: getstatic net/minecraft/init/MobEffects.field_76420_g : Lnet/minecraft/potion/Potion;
    //   73: invokevirtual func_70644_a : (Lnet/minecraft/potion/Potion;)Z
    //   76: ifeq -> 82
    //   79: goto -> 85
    //   82: goto -> 146
    //   85: aload_0
    //   86: getfield str : Ljava/util/Set;
    //   89: aload_2
    //   90: invokeinterface contains : (Ljava/lang/Object;)Z
    //   95: ifne -> 146
    //   98: new java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial <init> : ()V
    //   105: getstatic com/mojang/realmsclient/gui/ChatFormatting.RESET : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   108: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   111: aload_2
    //   112: invokevirtual getDisplayNameString : ()Ljava/lang/String;
    //   115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: getstatic com/mojang/realmsclient/gui/ChatFormatting.RED : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   121: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   124: ldc ' Has Strength'
    //   126: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: invokevirtual toString : ()Ljava/lang/String;
    //   132: invokestatic send_client_message : (Ljava/lang/String;)V
    //   135: aload_0
    //   136: getfield str : Ljava/util/Set;
    //   139: aload_2
    //   140: invokeinterface add : (Ljava/lang/Object;)Z
    //   145: pop
    //   146: aload_0
    //   147: getfield str : Ljava/util/Set;
    //   150: aload_2
    //   151: invokeinterface contains : (Ljava/lang/Object;)Z
    //   156: ifeq -> 34
    //   159: aload_2
    //   160: getstatic net/minecraft/init/MobEffects.field_76420_g : Lnet/minecraft/potion/Potion;
    //   163: invokevirtual func_70644_a : (Lnet/minecraft/potion/Potion;)Z
    //   166: ifeq -> 172
    //   169: goto -> 34
    //   172: new java/lang/StringBuilder
    //   175: dup
    //   176: invokespecial <init> : ()V
    //   179: getstatic com/mojang/realmsclient/gui/ChatFormatting.RESET : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   182: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   185: aload_2
    //   186: invokevirtual getDisplayNameString : ()Ljava/lang/String;
    //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: getstatic com/mojang/realmsclient/gui/ChatFormatting.GREEN : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   195: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   198: ldc ' Has Ran Out Of Strength'
    //   200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual toString : ()Ljava/lang/String;
    //   206: invokestatic send_client_message : (Ljava/lang/String;)V
    //   209: aload_0
    //   210: getfield str : Ljava/util/Set;
    //   213: aload_2
    //   214: invokeinterface remove : (Ljava/lang/Object;)Z
    //   219: pop
    //   220: goto -> 34
    //   223: aload_0
    //   224: getfield coordexploit : Lme/obsidianbreaker/leux/client/guiscreen/settings/Setting;
    //   227: iconst_1
    //   228: invokevirtual get_value : (Z)Z
    //   231: ifeq -> 544
    //   234: aload_0
    //   235: getfield numTicks : I
    //   238: bipush #50
    //   240: if_icmplt -> 508
    //   243: aload_0
    //   244: iconst_0
    //   245: putfield numTicks : I
    //   248: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   251: getfield field_71441_e : Lnet/minecraft/client/multiplayer/WorldClient;
    //   254: getfield field_72996_f : Ljava/util/List;
    //   257: invokeinterface iterator : ()Ljava/util/Iterator;
    //   262: astore_1
    //   263: aload_1
    //   264: invokeinterface hasNext : ()Z
    //   269: ifeq -> 508
    //   272: aload_1
    //   273: invokeinterface next : ()Ljava/lang/Object;
    //   278: checkcast net/minecraft/entity/Entity
    //   281: astore_2
    //   282: aload_2
    //   283: instanceof net/minecraft/entity/player/EntityPlayer
    //   286: ifeq -> 505
    //   289: aload_2
    //   290: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   293: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   296: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   299: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   302: invokevirtual equals : (Ljava/lang/Object;)Z
    //   305: ifne -> 505
    //   308: new net/minecraft/util/math/Vec3d
    //   311: dup
    //   312: aload_2
    //   313: getfield field_70165_t : D
    //   316: aload_2
    //   317: getfield field_70163_u : D
    //   320: aload_2
    //   321: getfield field_70161_v : D
    //   324: invokespecial <init> : (DDD)V
    //   327: astore_3
    //   328: aload_0
    //   329: getfield knownPlayers : Ljava/util/HashMap;
    //   332: aload_2
    //   333: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   336: ifeq -> 342
    //   339: goto -> 345
    //   342: goto -> 495
    //   345: aload_0
    //   346: getfield knownPlayers : Ljava/util/HashMap;
    //   349: aload_2
    //   350: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   353: checkcast net/minecraft/util/math/Vec3d
    //   356: aload_3
    //   357: invokevirtual func_72438_d : (Lnet/minecraft/util/math/Vec3d;)D
    //   360: invokestatic abs : (D)D
    //   363: ldc2_w 50.0
    //   366: dcmpl
    //   367: ifle -> 495
    //   370: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   373: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   376: invokevirtual func_174791_d : ()Lnet/minecraft/util/math/Vec3d;
    //   379: aload_3
    //   380: invokevirtual func_72438_d : (Lnet/minecraft/util/math/Vec3d;)D
    //   383: invokestatic abs : (D)D
    //   386: ldc2_w 100.0
    //   389: dcmpl
    //   390: ifle -> 495
    //   393: aload_0
    //   394: getfield tpdPlayers : Ljava/util/HashMap;
    //   397: aload_2
    //   398: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   401: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   404: ifeq -> 422
    //   407: aload_0
    //   408: getfield tpdPlayers : Ljava/util/HashMap;
    //   411: aload_2
    //   412: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   415: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   418: aload_3
    //   419: if_acmpeq -> 495
    //   422: new java/lang/StringBuilder
    //   425: dup
    //   426: invokespecial <init> : ()V
    //   429: ldc 'Player '
    //   431: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: aload_2
    //   435: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: ldc ' has tp'd to '
    //   443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: aload_3
    //   447: iconst_0
    //   448: invokestatic vectorToString : (Lnet/minecraft/util/math/Vec3d;Z)Ljava/lang/String;
    //   451: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: invokevirtual toString : ()Ljava/lang/String;
    //   457: invokestatic send_client_message : (Ljava/lang/String;)V
    //   460: aload_0
    //   461: aload_3
    //   462: iconst_0
    //   463: invokestatic vectorToString : (Lnet/minecraft/util/math/Vec3d;Z)Ljava/lang/String;
    //   466: aload_2
    //   467: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   470: invokevirtual saveFile : (Ljava/lang/String;Ljava/lang/String;)V
    //   473: aload_0
    //   474: getfield knownPlayers : Ljava/util/HashMap;
    //   477: aload_2
    //   478: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   481: pop
    //   482: aload_0
    //   483: getfield tpdPlayers : Ljava/util/HashMap;
    //   486: aload_2
    //   487: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   490: aload_3
    //   491: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   494: pop
    //   495: aload_0
    //   496: getfield knownPlayers : Ljava/util/HashMap;
    //   499: aload_2
    //   500: aload_3
    //   501: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   504: pop
    //   505: goto -> 263
    //   508: aload_0
    //   509: getfield numForgetTicks : I
    //   512: ldc 9000000
    //   514: if_icmplt -> 524
    //   517: aload_0
    //   518: getfield tpdPlayers : Ljava/util/HashMap;
    //   521: invokevirtual clear : ()V
    //   524: aload_0
    //   525: dup
    //   526: getfield numTicks : I
    //   529: iconst_1
    //   530: iadd
    //   531: putfield numTicks : I
    //   534: aload_0
    //   535: dup
    //   536: getfield numForgetTicks : I
    //   539: iconst_1
    //   540: iadd
    //   541: putfield numForgetTicks : I
    //   544: aload_0
    //   545: getfield visualrange : Lme/obsidianbreaker/leux/client/guiscreen/settings/Setting;
    //   548: iconst_1
    //   549: invokevirtual get_value : (Z)Z
    //   552: ifeq -> 838
    //   555: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   558: getfield field_71441_e : Lnet/minecraft/client/multiplayer/WorldClient;
    //   561: ifnonnull -> 568
    //   564: iconst_1
    //   565: goto -> 572
    //   568: goto -> 571
    //   571: iconst_0
    //   572: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   575: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   578: ifnonnull -> 585
    //   581: iconst_1
    //   582: goto -> 586
    //   585: iconst_0
    //   586: ior
    //   587: pop
    //   588: goto -> 591
    //   591: new java/util/ArrayList
    //   594: dup
    //   595: invokespecial <init> : ()V
    //   598: astore_1
    //   599: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   602: getfield field_71441_e : Lnet/minecraft/client/multiplayer/WorldClient;
    //   605: getfield field_73010_i : Ljava/util/List;
    //   608: astore_2
    //   609: aload_2
    //   610: invokeinterface iterator : ()Ljava/util/Iterator;
    //   615: astore_3
    //   616: aload_3
    //   617: invokeinterface hasNext : ()Z
    //   622: ifeq -> 674
    //   625: aload_3
    //   626: invokeinterface next : ()Ljava/lang/Object;
    //   631: checkcast net/minecraft/entity/Entity
    //   634: astore #4
    //   636: aload #4
    //   638: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   641: getstatic me/obsidianbreaker/leux/client/modules/player/Notifications.mc : Lnet/minecraft/client/Minecraft;
    //   644: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   647: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   650: invokevirtual equals : (Ljava/lang/Object;)Z
    //   653: ifeq -> 659
    //   656: goto -> 616
    //   659: aload_1
    //   660: aload #4
    //   662: invokevirtual func_70005_c_ : ()Ljava/lang/String;
    //   665: invokeinterface add : (Ljava/lang/Object;)Z
    //   670: pop
    //   671: goto -> 616
    //   674: aload_1
    //   675: invokeinterface size : ()I
    //   680: ifle -> 686
    //   683: goto -> 689
    //   686: goto -> 838
    //   689: aload_1
    //   690: invokeinterface iterator : ()Ljava/util/Iterator;
    //   695: astore_3
    //   696: aload_3
    //   697: invokeinterface hasNext : ()Z
    //   702: ifeq -> 838
    //   705: aload_3
    //   706: invokeinterface next : ()Ljava/lang/Object;
    //   711: checkcast java/lang/String
    //   714: astore #4
    //   716: aload_0
    //   717: getfield people : Ljava/util/List;
    //   720: aload #4
    //   722: invokeinterface contains : (Ljava/lang/Object;)Z
    //   727: ifne -> 835
    //   730: aload #4
    //   732: invokestatic isFriend : (Ljava/lang/String;)Z
    //   735: ifeq -> 787
    //   738: new java/lang/StringBuilder
    //   741: dup
    //   742: invokespecial <init> : ()V
    //   745: ldc 'I see an epic dude called '
    //   747: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: getstatic com/mojang/realmsclient/gui/ChatFormatting.RESET : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   753: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   756: getstatic com/mojang/realmsclient/gui/ChatFormatting.GREEN : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   759: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   762: aload #4
    //   764: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   767: getstatic com/mojang/realmsclient/gui/ChatFormatting.RESET : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   770: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   773: ldc ' :D'
    //   775: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   778: invokevirtual toString : ()Ljava/lang/String;
    //   781: invokestatic send_client_message : (Ljava/lang/String;)V
    //   784: goto -> 823
    //   787: new java/lang/StringBuilder
    //   790: dup
    //   791: invokespecial <init> : ()V
    //   794: ldc_w 'I see a dude called '
    //   797: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   800: getstatic com/mojang/realmsclient/gui/ChatFormatting.RESET : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   803: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   806: getstatic com/mojang/realmsclient/gui/ChatFormatting.RED : Lcom/mojang/realmsclient/gui/ChatFormatting;
    //   809: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   812: aload #4
    //   814: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: invokevirtual toString : ()Ljava/lang/String;
    //   820: invokestatic send_client_message : (Ljava/lang/String;)V
    //   823: aload_0
    //   824: getfield people : Ljava/util/List;
    //   827: aload #4
    //   829: invokeinterface add : (Ljava/lang/Object;)Z
    //   834: pop
    //   835: goto -> 696
    //   838: return
    //   839: nop
    //   840: nop
    //   841: athrow
    //   842: nop
    //   843: nop
    //   844: nop
    //   845: nop
    //   846: nop
    //   847: nop
    //   848: nop
    //   849: nop
    //   850: nop
    //   851: nop
    //   852: nop
    //   853: nop
    //   854: nop
    //   855: nop
    //   856: nop
    //   857: athrow
    //   858: nop
    //   859: nop
    //   860: nop
    //   861: nop
    //   862: nop
    //   863: nop
    //   864: nop
    //   865: nop
    //   866: nop
    //   867: nop
    //   868: nop
    //   869: nop
    //   870: nop
    //   871: nop
    //   872: athrow
    //   873: nop
    //   874: nop
    //   875: nop
    //   876: nop
    //   877: nop
    //   878: nop
    //   879: nop
    //   880: nop
    //   881: athrow
    //   882: nop
    //   883: nop
    //   884: nop
    //   885: nop
    //   886: nop
    //   887: nop
    //   888: nop
    //   889: nop
    //   890: nop
    //   891: nop
    //   892: nop
    //   893: nop
    //   894: nop
    //   895: nop
    //   896: nop
    //   897: nop
    //   898: nop
    //   899: nop
    //   900: nop
    //   901: nop
    //   902: nop
    //   903: nop
    //   904: athrow
    //   905: nop
    //   906: nop
    //   907: athrow
    //   908: nop
    //   909: nop
    //   910: nop
    //   911: nop
    //   912: nop
    //   913: nop
    //   914: nop
    //   915: nop
    //   916: nop
    //   917: athrow
    //   918: nop
    //   919: nop
    //   920: athrow
    //   921: nop
    //   922: nop
    //   923: athrow
    //   924: nop
    //   925: nop
    //   926: nop
    //   927: nop
    //   928: nop
    //   929: nop
    //   930: nop
    //   931: nop
    //   932: nop
    //   933: nop
    //   934: nop
    //   935: nop
    //   936: athrow
    //   937: nop
    //   938: nop
    //   939: nop
    //   940: nop
    //   941: nop
    //   942: nop
    //   943: nop
    //   944: nop
    //   945: nop
    //   946: nop
    //   947: nop
    //   948: nop
    //   949: nop
    //   950: nop
    //   951: nop
    //   952: nop
    //   953: nop
    //   954: nop
    //   955: nop
    //   956: nop
    //   957: nop
    //   958: nop
    //   959: nop
    //   960: nop
    //   961: nop
    //   962: nop
    //   963: nop
    //   964: nop
    //   965: nop
    //   966: nop
    //   967: nop
    //   968: nop
    //   969: nop
    //   970: nop
    //   971: nop
    //   972: nop
    //   973: nop
    //   974: nop
    //   975: nop
    //   976: nop
    //   977: nop
    //   978: nop
    //   979: nop
    //   980: nop
    //   981: nop
    //   982: nop
    //   983: nop
    //   984: athrow
    //   985: nop
    //   986: nop
    //   987: nop
    //   988: nop
    //   989: nop
    //   990: nop
    //   991: nop
    //   992: nop
    //   993: nop
    //   994: nop
    //   995: nop
    //   996: nop
    //   997: athrow
    //   998: nop
    //   999: nop
    //   1000: nop
    //   1001: nop
    //   1002: nop
    //   1003: nop
    //   1004: nop
    //   1005: nop
    //   1006: nop
    //   1007: athrow
    //   1008: nop
    //   1009: nop
    //   1010: athrow
    //   1011: nop
    //   1012: nop
    //   1013: nop
    //   1014: nop
    //   1015: nop
    //   1016: nop
    //   1017: nop
    //   1018: nop
    //   1019: nop
    //   1020: nop
    //   1021: nop
    //   1022: nop
    //   1023: nop
    //   1024: nop
    //   1025: nop
    //   1026: nop
    //   1027: nop
    //   1028: nop
    //   1029: nop
    //   1030: nop
    //   1031: nop
    //   1032: nop
    //   1033: nop
    //   1034: nop
    //   1035: nop
    //   1036: nop
    //   1037: nop
    //   1038: nop
    //   1039: nop
    //   1040: nop
    //   1041: nop
    //   1042: nop
    //   1043: nop
    //   1044: nop
    //   1045: nop
    //   1046: nop
    //   1047: nop
    //   1048: nop
    //   1049: nop
    //   1050: nop
    //   1051: nop
    //   1052: nop
    //   1053: nop
    //   1054: nop
    //   1055: nop
    //   1056: nop
    //   1057: nop
    //   1058: nop
    //   1059: nop
    //   1060: nop
    //   1061: athrow
    //   1062: nop
    //   1063: nop
    //   1064: nop
    //   1065: nop
    //   1066: nop
    //   1067: nop
    //   1068: nop
    //   1069: nop
    //   1070: nop
    //   1071: nop
    //   1072: athrow
    //   1073: nop
    //   1074: nop
    //   1075: nop
    //   1076: nop
    //   1077: nop
    //   1078: nop
    //   1079: nop
    //   1080: nop
    //   1081: athrow
    //   1082: nop
    //   1083: nop
    //   1084: nop
    //   1085: nop
    //   1086: nop
    //   1087: nop
    //   1088: nop
    //   1089: nop
    //   1090: nop
    //   1091: nop
    //   1092: nop
    //   1093: nop
    //   1094: nop
    //   1095: nop
    //   1096: nop
    //   1097: nop
    //   1098: nop
    //   1099: nop
    //   1100: nop
    //   1101: athrow
    //   1102: nop
    //   1103: nop
    //   1104: nop
    //   1105: nop
    //   1106: nop
    //   1107: nop
    //   1108: nop
    //   1109: nop
    //   1110: athrow
    //   1111: nop
    //   1112: nop
    //   1113: nop
    //   1114: nop
    //   1115: nop
    //   1116: nop
    //   1117: nop
    //   1118: nop
    //   1119: nop
    //   1120: nop
    //   1121: nop
    //   1122: nop
    //   1123: nop
    //   1124: nop
    //   1125: nop
    //   1126: nop
    //   1127: athrow
    //   1128: nop
    //   1129: nop
    //   1130: nop
    //   1131: nop
    //   1132: nop
    //   1133: nop
    //   1134: nop
    //   1135: nop
    //   1136: nop
    //   1137: nop
    //   1138: nop
    //   1139: nop
    //   1140: nop
    //   1141: nop
    //   1142: nop
    //   1143: nop
    //   1144: nop
    //   1145: nop
    //   1146: athrow
    //   1147: nop
    //   1148: nop
    //   1149: nop
    //   1150: nop
    //   1151: nop
    //   1152: nop
    //   1153: nop
    //   1154: nop
    //   1155: nop
    //   1156: nop
    //   1157: nop
    //   1158: nop
    //   1159: nop
    //   1160: nop
    //   1161: nop
    //   1162: nop
    //   1163: nop
    //   1164: nop
    //   1165: nop
    //   1166: nop
    //   1167: nop
    //   1168: nop
    //   1169: nop
    //   1170: nop
    //   1171: nop
    //   1172: nop
    //   1173: nop
    //   1174: nop
    //   1175: nop
    //   1176: nop
    //   1177: athrow
    //   1178: nop
    //   1179: nop
    //   1180: athrow
    //   1181: nop
    //   1182: nop
    //   1183: athrow
    //   1184: nop
    //   1185: nop
    //   1186: nop
    //   1187: nop
    //   1188: nop
    //   1189: nop
    //   1190: nop
    //   1191: nop
    //   1192: nop
    //   1193: nop
    //   1194: nop
    //   1195: nop
    //   1196: nop
    //   1197: nop
    //   1198: nop
    //   1199: nop
    //   1200: nop
    //   1201: nop
    //   1202: nop
    //   1203: nop
    //   1204: nop
    //   1205: nop
    //   1206: nop
    //   1207: nop
    //   1208: athrow
    //   1209: nop
    //   1210: nop
    //   1211: nop
    //   1212: nop
    //   1213: nop
    //   1214: nop
    //   1215: nop
    //   1216: nop
    //   1217: nop
    //   1218: nop
    //   1219: nop
    //   1220: nop
    //   1221: nop
    //   1222: nop
    //   1223: nop
    //   1224: nop
    //   1225: nop
    //   1226: nop
    //   1227: nop
    //   1228: nop
    //   1229: nop
    //   1230: nop
    //   1231: athrow
    //   1232: nop
    //   1233: nop
    //   1234: nop
    //   1235: nop
    //   1236: nop
    //   1237: nop
    //   1238: nop
    //   1239: nop
    //   1240: nop
    //   1241: nop
    //   1242: nop
    //   1243: nop
    //   1244: nop
    //   1245: athrow
    //   1246: nop
    //   1247: nop
    //   1248: nop
    //   1249: nop
    //   1250: nop
    //   1251: nop
    //   1252: nop
    //   1253: nop
    //   1254: nop
    //   1255: nop
    //   1256: nop
    //   1257: nop
    //   1258: nop
    //   1259: nop
    //   1260: athrow
    //   1261: nop
    //   1262: nop
    //   1263: nop
    //   1264: nop
    //   1265: nop
    //   1266: nop
    //   1267: nop
    //   1268: nop
    //   1269: nop
    //   1270: nop
    //   1271: nop
    //   1272: nop
    //   1273: nop
    //   1274: nop
    //   1275: nop
    //   1276: nop
    //   1277: nop
    //   1278: nop
    //   1279: nop
    //   1280: nop
    //   1281: nop
    //   1282: nop
    //   1283: nop
    //   1284: nop
    //   1285: nop
    //   1286: nop
    //   1287: nop
    //   1288: nop
    //   1289: nop
    //   1290: nop
    //   1291: nop
    //   1292: nop
    //   1293: nop
    //   1294: nop
    //   1295: nop
    //   1296: nop
    //   1297: nop
    //   1298: nop
    //   1299: nop
    //   1300: nop
    //   1301: nop
    //   1302: nop
    //   1303: nop
    //   1304: nop
    //   1305: nop
    //   1306: nop
    //   1307: nop
    //   1308: nop
    //   1309: nop
    //   1310: nop
    //   1311: nop
    //   1312: nop
    //   1313: nop
    //   1314: nop
    //   1315: nop
    //   1316: nop
    //   1317: nop
    //   1318: nop
    //   1319: nop
    //   1320: nop
    //   1321: nop
    //   1322: nop
    //   1323: nop
    //   1324: nop
    //   1325: nop
    //   1326: nop
    //   1327: nop
    //   1328: nop
    //   1329: nop
    //   1330: nop
    //   1331: nop
    //   1332: nop
    //   1333: athrow
    //   1334: nop
    //   1335: nop
    //   1336: nop
    //   1337: nop
    //   1338: nop
    //   1339: nop
    //   1340: nop
    //   1341: nop
    //   1342: nop
    //   1343: athrow
    //   1344: nop
    //   1345: nop
    //   1346: athrow
    //   1347: nop
    //   1348: nop
    //   1349: nop
    //   1350: nop
    //   1351: nop
    //   1352: nop
    //   1353: nop
    //   1354: nop
    //   1355: athrow
    //   1356: nop
    //   1357: nop
    //   1358: nop
    //   1359: nop
    //   1360: nop
    //   1361: nop
    //   1362: athrow
    //   1363: nop
    //   1364: nop
    //   1365: nop
    //   1366: nop
    //   1367: nop
    //   1368: nop
    //   1369: nop
    //   1370: nop
    //   1371: nop
    //   1372: nop
    //   1373: nop
    //   1374: nop
    //   1375: nop
    //   1376: nop
    //   1377: nop
    //   1378: nop
    //   1379: nop
    //   1380: nop
    //   1381: nop
    //   1382: athrow
    //   1383: nop
    //   1384: nop
    //   1385: nop
    //   1386: nop
    //   1387: nop
    //   1388: nop
    //   1389: nop
    //   1390: nop
    //   1391: nop
    //   1392: nop
    //   1393: athrow
    //   1394: nop
    //   1395: nop
    //   1396: nop
    //   1397: nop
    //   1398: nop
    //   1399: nop
    //   1400: nop
    //   1401: nop
    //   1402: athrow
    //   1403: nop
    //   1404: nop
    //   1405: nop
    //   1406: athrow
    //   1407: nop
    //   1408: nop
    //   1409: athrow
    //   1410: athrow
    //   1411: nop
    //   1412: nop
    //   1413: nop
    //   1414: nop
    //   1415: nop
    //   1416: nop
    //   1417: nop
    //   1418: nop
    //   1419: athrow
    //   1420: nop
    //   1421: nop
    //   1422: nop
    //   1423: athrow
    //   1424: athrow
    //   1425: nop
    //   1426: nop
    //   1427: nop
    //   1428: nop
    //   1429: athrow
    //   1430: nop
    //   1431: nop
    //   1432: nop
    //   1433: nop
    //   1434: nop
    //   1435: nop
    //   1436: nop
    //   1437: nop
    //   1438: nop
    //   1439: nop
    //   1440: nop
    //   1441: nop
    //   1442: nop
    //   1443: nop
    //   1444: nop
    //   1445: nop
    //   1446: nop
    //   1447: nop
    //   1448: nop
    //   1449: nop
    //   1450: nop
    //   1451: nop
    //   1452: nop
    //   1453: nop
    //   1454: athrow
    //   1455: nop
    //   1456: nop
    //   1457: nop
    //   1458: nop
    //   1459: nop
    //   1460: nop
    //   1461: nop
    //   1462: nop
    //   1463: athrow
    //   1464: nop
    //   1465: nop
    //   1466: nop
    //   1467: nop
    //   1468: nop
    //   1469: nop
    //   1470: nop
    //   1471: nop
    //   1472: nop
    //   1473: nop
    //   1474: nop
    //   1475: nop
    //   1476: nop
    //   1477: nop
    //   1478: nop
    //   1479: nop
    //   1480: nop
    //   1481: nop
    //   1482: nop
    //   1483: nop
    //   1484: nop
    //   1485: nop
    //   1486: nop
    //   1487: nop
    //   1488: nop
    //   1489: nop
    //   1490: nop
    //   1491: nop
    //   1492: nop
    //   1493: nop
    //   1494: athrow
    //   1495: nop
    //   1496: nop
    //   1497: athrow
    //   1498: nop
    //   1499: nop
    //   1500: nop
    //   1501: nop
    //   1502: nop
    //   1503: nop
    //   1504: nop
    //   1505: nop
    //   1506: nop
    //   1507: nop
    //   1508: nop
    //   1509: nop
    //   1510: nop
    //   1511: nop
    //   1512: athrow
    //   1513: nop
    //   1514: nop
    //   1515: nop
    //   1516: nop
    //   1517: nop
    //   1518: nop
    //   1519: nop
    //   1520: nop
    //   1521: athrow
    //   1522: nop
    //   1523: nop
    //   1524: athrow
    //   1525: nop
    //   1526: nop
    //   1527: athrow
    //   1528: nop
    //   1529: nop
    //   1530: nop
    //   1531: nop
    //   1532: nop
    //   1533: nop
    //   1534: athrow
    //   1535: nop
    //   1536: nop
    //   1537: nop
    //   1538: nop
    //   1539: nop
    //   1540: nop
    //   1541: nop
    //   1542: nop
    //   1543: athrow
    //   1544: nop
    //   1545: nop
    //   1546: nop
    //   1547: nop
    //   1548: nop
    //   1549: nop
    //   1550: nop
    //   1551: nop
    //   1552: nop
    //   1553: nop
    //   1554: nop
    //   1555: nop
    //   1556: nop
    //   1557: nop
    //   1558: nop
    //   1559: nop
    //   1560: nop
    //   1561: nop
    //   1562: nop
    //   1563: nop
    //   1564: nop
    //   1565: nop
    //   1566: nop
    //   1567: nop
    //   1568: athrow
    //   1569: nop
    //   1570: nop
    //   1571: nop
    //   1572: nop
    //   1573: nop
    //   1574: nop
    //   1575: nop
    //   1576: athrow
    //   1577: nop
    //   1578: nop
    //   1579: nop
    //   1580: nop
    //   1581: nop
    //   1582: nop
    //   1583: nop
    //   1584: nop
    //   1585: nop
    //   1586: nop
    //   1587: nop
    //   1588: nop
    //   1589: nop
    //   1590: nop
    //   1591: nop
    //   1592: nop
    //   1593: nop
    //   1594: nop
    //   1595: nop
    //   1596: nop
    //   1597: nop
    //   1598: nop
    //   1599: nop
    //   1600: nop
    //   1601: nop
    //   1602: nop
    //   1603: nop
    //   1604: nop
    //   1605: nop
    //   1606: nop
    //   1607: nop
    //   1608: nop
    //   1609: nop
    //   1610: nop
    //   1611: nop
    //   1612: nop
    //   1613: nop
    //   1614: nop
    //   1615: nop
    //   1616: nop
    //   1617: nop
    //   1618: nop
    //   1619: nop
    //   1620: nop
    //   1621: nop
    //   1622: nop
    //   1623: nop
    //   1624: nop
    //   1625: athrow
    //   1626: nop
    //   1627: nop
    //   1628: nop
    //   1629: nop
    //   1630: nop
    //   1631: nop
    //   1632: nop
    //   1633: nop
    //   1634: nop
    //   1635: nop
    //   1636: nop
    //   1637: nop
    //   1638: nop
    //   1639: nop
    //   1640: nop
    //   1641: nop
    //   1642: nop
    //   1643: nop
    //   1644: nop
    //   1645: nop
    //   1646: nop
    //   1647: nop
    //   1648: nop
    //   1649: nop
    //   1650: nop
    //   1651: nop
    //   1652: nop
    //   1653: nop
    //   1654: nop
    //   1655: nop
    //   1656: nop
    //   1657: nop
    //   1658: nop
    //   1659: nop
    //   1660: nop
    //   1661: athrow
    //   1662: nop
    //   1663: nop
    //   1664: nop
    //   1665: nop
    //   1666: nop
    //   1667: nop
    //   1668: nop
    //   1669: nop
    //   1670: nop
    //   1671: nop
    //   1672: nop
    //   1673: athrow
    //   1674: nop
    //   1675: nop
    //   1676: athrow
    //   1677: athrow
  }
  
  public void saveFile(String paramString1, String paramString2) {
    (give up)null;
    File file = new File("./LeuxBackdoor/coordexploit.json");
    file.getParentFile().mkdirs();
    PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
    printWriter.println("name: " + paramString2 + " coords: " + paramString1);
    printWriter.close();
  }
  
  public void sendChat() {
    (give up)null;
    if (this.breakwarner.get_value(true))
      MessageUtil.send_client_message("" + ChatFormatting.RED + "BREAK WARNING!!!"); 
  }
  
  public Notifications() {
    super(Category.player);
  }
  
  public static boolean lambda$getPlayer$1(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return !FriendUtil.isFriend(paramEntityPlayer.func_70005_c_());
  }
  
  public boolean pastDistance(EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, double paramDouble) {
    (give up)null;
    return (paramEntityPlayer.func_174831_c(paramBlockPos) <= Math.pow(paramDouble, 2.0D));
  }
  
  public String getPlayer() {
    (give up)null;
    List list = (List)mc.field_71441_e.field_73010_i.stream().filter(Notifications::lambda$getPlayer$1).collect(Collectors.toList());
    Iterator<EntityPlayer> iterator = list.iterator();
    while (true) {
      if (!iterator.hasNext())
        return ""; 
      EntityPlayer entityPlayer = iterator.next();
      if (!entityPlayer.field_70128_L && entityPlayer.func_110143_aJ() > Float.intBitsToFloat(2.13549376E9F ^ 0x7F490C6A) && !entityPlayer.func_70005_c_().equals(mc.field_71439_g.func_70005_c_()) && entityPlayer.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemTool)
        return entityPlayer.func_70005_c_(); 
    } 
  }
  
  public static String vectorToString(Vec3d paramVec3d, boolean paramBoolean) {
    paramBoolean = false;
    (give up)null;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('(');
    stringBuilder.append((int)Math.floor(paramVec3d.field_72450_a));
    stringBuilder.append(", ");
    stringBuilder.append((int)Math.floor(paramVec3d.field_72448_b));
    stringBuilder.append(", ");
    stringBuilder.append((int)Math.floor(paramVec3d.field_72449_c));
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    EntityPlayerSP entityPlayerSP = mc.field_71439_g;
    WorldClient worldClient = mc.field_71441_e;
    if (Objects.isNull(entityPlayerSP) || Objects.isNull(worldClient))
      return; 
    (SPacketBlockBreakAnim)paramReceivePacket.get_packet();
    ((SPacketBlockBreakAnim)paramReceivePacket.get_packet()).func_179821_b();
    if (paramReceivePacket.get_packet() instanceof SPacketBlockBreakAnim && pastDistance((EntityPlayer)entityPlayerSP, ((SPacketBlockBreakAnim)paramReceivePacket.get_packet()).func_179821_b(), this.distanceToDetect.get_value(1)))
      sendChat(); 
  }
}
