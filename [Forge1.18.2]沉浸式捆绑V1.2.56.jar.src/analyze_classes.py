import os
import re
import json

SRC_DIR = r"c:\Users\小亮傻逼\Desktop\cfr_output\abductionmod\content"
DST_DIR = r"C:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1\src\main\java\com\dev\am\immersive_restraints"

CLASS_MAPPING = {
    "aS": "init/ModItems",
    "g_0": "init/ModSounds",
    "aK": "item/BondageItem",
    "aZ": "item/GeoArmorBase",
    "b_0": "item/BondageProperties",
    "h_0": "item/PartVisibility",
    "bb_0": "item/VulnerabilityType",
    "bx": "mechanic/BondageManager",
    "bw": "mechanic/NBTManager",
    "bm": "mechanic/BondageState",
    "O": "mechanic/StruggleMinigame",
    "i_0": "mechanic/MinigameDirection",
    "C": "input/KeyBindings",
    "W": "command/ModCommands",
    "bs": "gag/GagTransformer",
    "a_0": "gag/GagScript",
    "aA": "gag/GagType",
    "aB": "gag/GagLevel",
    "aC": "gag/GagEffect",
    "aD": "blindfold/BlindfoldType",
    "aE": "blindfold/BlindfoldEffect",
    "aF": "blindfold/BlindfoldOverlay",
    "aG": "curios/CurioSlotType",
    "aH": "curios/CurioIntegration",
    "aI": "curios/CurioRender",
    "aJ": "network/PacketHandler",
    "aQ": "network/TyingPacket",
    "aR": "network/UntyingPacket",
    "aP": "network/AnimationSyncPacket",
    "aO": "network/StrugglePacket",
    "aN": "network/StateSyncPacket",
    "aT": "render/PlayerModelModifier",
    "aU": "render/ArmorLayerRenderer",
    "aV": "render/BondageRenderer",
    "aX": "render/GeoArmorRenderer",
    "aE": "render/BlindfoldOverlay",
    "aW": "animation/AnimationController",
    "aY": "animation/AnimationState",
    "ba_0": "animation/AnimationType",
    "ay_0": "animation/PlayerAnimProvider",
    "ax_0": "animation/BendyLibIntegration",
    "av_0": "animation/PlayerAnimLibIntegration",
    "bA": "data/DataProvider",
    "bB": "data/BlockModelProvider",
    "bC": "data/ItemModelProvider",
    "bD": "data/LootTableProvider",
    "bE": "data/LanguageProvider",
    "bF": "data/RecipeProvider",
    "bG": "data/TagProvider",
    "bf_0": "data/ModBlockTags",
    "bg_0": "data/ModItemTags",
    "bd_0": "data/ModLootTables",
    "bc_0": "data/ModRecipes",
    "be_0": "data/ModLanguages",
    "bH": "block/ModBlocks",
    "bI": "block/BondageBlock",
    "bJ": "block/StocksBlock",
    "bK": "block/CrossBlock",
    "bL": "block/StAndrewsCrossBlock",
    "bM": "blockentity/ModBlockEntities",
    "bN": "blockentity/StocksBlockEntity",
    "bO": "blockentity/CrossBlockEntity",
    "bP": "blockentity/BlockEntityRenderer",
    "bQ": "config/ModConfig",
    "bR": "config/ClientConfig",
    "bS": "config/CommonConfig",
    "bT": "config/ServerConfig",
    "bU": "event/EventHandler",
    "bV": "event/ClientEventHandler",
    "bW": "event/PlayerEventHandler",
    "bX": "event/EntityEventHandler",
    "bY": "event/RenderEventHandler",
    "bZ": "event/InteractEventHandler",
    "B": "util/ModUtil",
    "D": "util/ColorUtil",
    "E": "util/MathUtil",
    "F": "util/RenderUtil",
    "G": "util/TextUtil",
    "H": "util/NBTUtil",
    "I": "util/ResourceUtil",
    "J": "util/SoundUtil",
    "K": "util/EnchantmentUtil",
    "L": "util/DamageSourceUtil",
    "M": "util/TranslationUtil",
    "N": "util/CurioUtil",
    "P": "util/AnimationUtil",
    "Q": "util/NetworkUtil",
    "R": "util/PlayerUtil",
    "S": "util/ItemStackUtil",
    "T": "util/EntityUtil",
    "U": "compat/CompatManager",
    "V": "compat/CuriosCompat",
    "X": "compat/GeckoLibCompat",
    "Y": "compat/BendyLibCompat",
    "Z": "compat/PlayerAnimCompat",
    "A": "core/ModCore",
    "bj": "item/KeyItem",
    "bk": "item/LockpickItem",
    "bl": "item/ScissorsItem",
    "bm_0": "item/RestraintItem",
    "bn": "item/HandcuffItem",
    "bo": "item/RopeItem",
    "bp": "item/GagItem",
    "bq": "item/BlindfoldItem",
    "br": "item/CollarItem",
    "bt": "item/MittenItem",
    "bu": "item/StraitjacketItem",
    "bv": "item/ClothingItem",
    "c_0": "item/ArmorSetType",
    "d_0": "item/BondageMaterial",
    "e_0": "item/ResistanceLevel",
    "f_0": "item/BindType",
    "ab_0": "item/KeyPredicate",
    "ac_0": "item/Vulnerability",
    "ad_0": "item/AutoLockProperty",
    "ae_0": "item/PopOffProperty",
    "af_0": "item/SoundProperty",
    "ag_0": "item/PartVisibilityType",
    "ah_0": "item/BindHandsProperty",
    "ai_0": "item/BindLegsProperty",
    "aj_0": "item/ResistanceProperty",
    "ak_0": "item/VulnerabilityProperty",
    "al_0": "item/TooltipProperty",
    "am_0": "item/CreativeTabProperty",
    "an_0": "item/ColorProperty",
    "ao_0": "item/DyeableProperty",
    "ap_0": "item/EquipSoundProperty",
    "aq_0": "item/UnequipSoundProperty",
    "ar_0": "item/ModelProperty",
    "as_0": "item/TextureProperty",
    "at_0": "item/AnimationProperty",
    "au_0": "item/SlotTypeProperty",
    "w_0": "mechanic/StruggleArrow",
    "x_0": "mechanic/StruggleResult",
    "y_0": "mechanic/TyingProgress",
    "z_0": "mechanic/UntyingProgress",
    "u_0": "mechanic/PlayerBondageState",
    "v_0": "mechanic/BondageSlot",
    "s_0": "mechanic/EscapeMethod",
    "r_0": "mechanic/ResistanceCalculator",
    "q_0": "mechanic/VulnerabilityCalculator",
    "p_0": "mechanic/StruggleCalculator",
    "o_0": "mechanic/MinigameConfig",
    "n_0": "mechanic/MinigameArrow",
    "m_0": "mechanic/MinigameResult",
    "l_0": "mechanic/KeyValidation",
    "k_0": "mechanic/LockpickMinigame",
    "j_0": "mechanic/ScissorMinigame",
    "aa_0": "gag/GagScriptEngine",
    "W": "command/ModCommands",
    "M": "util/TranslationUtil",
}

def analyze_class(filepath):
    strings = []
    extends = None
    implements = []
    methods = []
    fields = []
    
    try:
        with open(filepath, 'r', encoding='utf-8', errors='replace') as f:
            content = f.read()
        
        string_pattern = r'"([^"]*)"'
        strings = re.findall(string_pattern, content)
        
        extends_match = re.search(r'class\s+\w+\s+extends\s+(\w+)', content)
        if extends_match:
            extends = extends_match.group(1)
        
        impl_match = re.search(r'implements\s+([\w,\s]+)', content)
        if impl_match:
            implements = [i.strip() for i in impl_match.group(1).split(',')]
        
        method_pattern = r'(public|protected|private)\s+[\w<>\[\],\s]+\s+(\w+)\s*\('
        methods = re.findall(method_pattern, content)
        methods = [m[1] for m in methods]
        
    except Exception as e:
        pass
    
    return {
        'strings': strings[:20],
        'extends': extends,
        'implements': implements,
        'methods': methods[:15]
    }

results = {}
for filename in os.listdir(SRC_DIR):
    if filename.endswith('.java'):
        classname = filename[:-4]
        filepath = os.path.join(SRC_DIR, filename)
        info = analyze_class(filepath)
        mapped = CLASS_MAPPING.get(classname, None)
        results[classname] = {
            'mapped': mapped,
            'strings': info['strings'],
            'extends': info['extends'],
            'implements': info['implements'],
            'methods': info['methods']
        }

mapped_count = sum(1 for v in results.values() if v['mapped'])
total_count = len(results)

print(f"Total classes: {total_count}")
print(f"Mapped classes: {mapped_count}")
print(f"Unmapped classes: {total_count - mapped_count}")
print()

unmapped = {k: v for k, v in results.items() if not v['mapped']}
for name, info in sorted(unmapped.items()):
    key_strings = [s for s in info['strings'] if len(s) > 2 and not s.startswith('/') and 'java' not in s.lower()]
    print(f"  {name}.java -> extends:{info['extends']} impl:{info['implements']} strings:{key_strings[:5]} methods:{info['methods'][:5]}")

output_path = os.path.join(DST_DIR, '..', '..', '..', '..', 'class_analysis.json')
os.makedirs(os.path.dirname(output_path), exist_ok=True)
with open(output_path, 'w', encoding='utf-8') as f:
    json.dump(results, f, indent=2, ensure_ascii=False)
print(f"\nFull analysis saved to class_analysis.json")