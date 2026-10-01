package abductionmod.content;

import net.minecraftforge.common.ForgeConfigSpec;

public class c {
  public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  
  public static final ForgeConfigSpec SPEC;
  
  public static ForgeConfigSpec.ConfigValue<String> allowedHosts;
  
  public static ForgeConfigSpec.ConfigValue<String> tiedAllowedCommands = BUILDER.comment("Enabled commands when tied").define("tiedAllowedCommands", "gamemode, gamerule, utm, time, tp, weather, difficulty, give, summon, experience, kill, ban, kick, op, pardon, deop, norp, helpless, adjust, msg, w, whisper, utm, untie, dc, me");
  
  public static ForgeConfigSpec.ConfigValue<String> helplessAllowedCommands = BUILDER.comment("Enabled commands when helpless").define("helplessAllowedCommands", "norp, me, helpless, adjust, msg, w, whisper, utm");
  
  static {
    allowedHosts = BUILDER.comment("Allowed hosts for skins and textures").define("allowedHosts", "pasteboard.co, gcdnb.pbrd.co, imgur.com, s.namemc.com, i.imgur.com, discord.com, discordapp.net, minecraftskins.com, i.ibb.co, ibb.co, i.postimg.cc, www.minecraftskins.com");
    BUILDER.pop();
    SPEC = BUILDER.build();
  }
  
  static {
    BUILDER.push("Nice 'n Tight Settings");
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\c.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */