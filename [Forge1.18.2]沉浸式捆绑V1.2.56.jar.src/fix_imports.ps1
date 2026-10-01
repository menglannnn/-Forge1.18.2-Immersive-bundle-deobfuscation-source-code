$ErrorActionPreference = 'Continue'
$base = (Resolve-Path 'C:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1').Path
$srcBase = "$base\src\main\java\com\dev\am\immersive_restraints"

$mapLines = @(
    'A=core/ModCore'
    'a_0=core/CreativeTab'
    'B=util/ModUtil'
    'C=input/KeyBindings'
    'D=item/PartVisibilityEnum'
    'd_0=item/BondageMaterial'
    'E=animation/AnimationId'
    'e_0=item/ResistanceLevel'
    'F=command/CommandUtil'
    'f_0=command/TalkAreaCommand'
    'G=render/GeoArmorModelBase'
    'g_0=init/ModSounds'
    'H=render/GeoArmorRendererBase'
    'h_0=item/PartVisibility'
    'I=render/ColorMarkModel'
    'i_0=mechanic/MinigameDirection'
    'J=render/GeoItemRendererBase'
    'j_0=mechanic/MinigameArrowData'
    'K=render/StruggleGeoRenderer'
    'k_0=animation/StruggleAnimations'
    'L=render/DynamicItemModel'
    'l_0=mechanic/KeyValidation'
    'M=util/ConfigBase'
    'm_0=animation/AnimationParser'
    'N=config/NiceNTightConfig'
    'n_0=mechanic/MinigameCamera'
    'O=mechanic/StruggleMinigame'
    'o_0=block/BlockVariantNames'
    'P=config/TextureConfig'
    'p_0=block/ModBlockProperties'
    'Q=command/AdjustCommand'
    'q_0=block/ModBlock'
    'R=command/ArmBindStyleCommand'
    'r_0=block/LockableDoorBlock'
    'S=command/ArmorVisibilityCommand'
    's_0=block/DirectionalBondageBlock'
    'T=command/ClothesProtectionCommand'
    'U=command/SaveClothesCommand'
    'u_0=block/BondageSlabBlock'
    'V=command/SetCollarNameCommand'
    'v_0=block/BondageStairBlock'
    'W=config/NiceNTightSettings'
    'w_0=render/BondageTextureHelper'
    'X=command/GagTextStyleCommand'
    'x_0=render/RenderHelper'
    'Y=command/HelplessCommand'
    'y_0=mechanic/TyingProgress'
    'Z=command/LegsTyingCommand'
    'z_0=mechanic/UntyingProgress'
    'aA=gag/GagType'
    'aB=gag/GagLevel'
    'aC=gag/GagEffect'
    'aD=blindfold/BlindfoldType'
    'aE=blindfold/BlindfoldEffect'
    'aF=blindfold/BlindfoldOverlay'
    'aG=curios/CurioSlotType'
    'aH=curios/CurioIntegration'
    'aI=curios/CurioRender'
    'aJ=network/PacketHandler'
    'aK=item/BondageItem'
    'aL=network/TyingPacket'
    'aM=network/UntyingPacket'
    'aN=network/AnimationSyncPacket'
    'aO=network/StrugglePacket'
    'aP=network/StateSyncPacket'
    'aQ=network/SkinSyncPacket'
    'aR=network/ConfigSyncPacket'
    'aS=init/ModItems'
    'aT=render/PlayerModelModifier'
    'aU=render/ArmorLayerRenderer'
    'aV=render/BondageRenderer'
    'aW=animation/AnimationController'
    'aX=render/GeoArmorRenderer'
    'aY=animation/AnimationState'
    'aZ=item/GeoArmorBase'
    'aa_0=gag/GagScriptEngine'
    'ab_0=item/KeyPredicate'
    'ac_0=item/Vulnerability'
    'ad_0=item/AutoLockProperty'
    'ae_0=item/PopOffProperty'
    'af_0=item/SoundProperty'
    'ag_0=item/PartVisibilityType'
    'ah_0=item/BindHandsProperty'
    'ai_0=item/BindLegsProperty'
    'aj_0=item/ResistanceProperty'
    'ak_0=item/VulnerabilityProperty'
    'al_0=item/TooltipProperty'
    'am_0=item/CreativeTabProperty'
    'an_0=item/ColorProperty'
    'ao_0=item/DyeableProperty'
    'ap_0=item/EquipSoundProperty'
    'aq_0=item/UnequipSoundProperty'
    'ar_0=item/ModelProperty'
    'as_0=item/TextureProperty'
    'at_0=item/AnimationProperty'
    'au_0=item/SlotTypeProperty'
    'av_0=animation/PlayerAnimProvider'
    'aw_0=animation/BendyLibIntegration'
    'ax_0=animation/PlayerAnimLibIntegration'
    'ay_0=animation/AnimLibCompat'
    'az_0=animation/AnimationHelper'
    'b_0=item/BondageProperties'
    'ba_0=animation/AnimationType'
    'bb_0=item/VulnerabilityType'
    'bc_0=data/ModRecipes'
    'bd_0=data/ModLootTables'
    'be_0=data/ModLanguages'
    'bf_0=data/ModBlockTags'
    'bg_0=data/ModItemTags'
    'bh=util/TooltipFormatter'
    'bi=util/TextComponentUtil'
    'bj=network/ChannelHandler'
    'bk=network/PacketEncoder'
    'bl=mechanic/StruggleLogic'
    'bm=mechanic/BondageState'
    'bn=mechanic/HandBindLogic'
    'bo=mechanic/LegBindLogic'
    'bq=mechanic/BlindfoldLogic'
    'br=command/TalkAreaCommand2'
    'bs=gag/GagTransformer'
    'bt=mechanic/LegTyingLogic'
    'bu=client/AgeConfirmScreen'
    'bv=client/WardrobeScreen'
    'bw=mechanic/NBTManager'
    'bx=mechanic/BondageManager'
    'bz=item/ArmorDrawnProperty'
    'bA=data/DataProvider'
    'bB=data/BlockModelProvider'
    'bC=data/ItemModelProvider'
    'bD=data/SkinProvider'
    'bE=data/LanguageProvider'
    'bF=data/RecipeProvider'
    'bG=data/TagProvider'
    'bH=block/ModBlocks'
    'bI=block/BondageBlock'
    'bJ=block/StocksBlock'
    'bK=block/CrossBlock'
    'bL=block/StAndrewsCrossBlock'
    'bM=blockentity/ModBlockEntities'
    'bN=blockentity/StocksBlockEntity'
    'bO=blockentity/CrossBlockEntity'
    'bP=blockentity/BlockEntityRenderer'
    'bQ=config/ModConfig'
    'bR=config/ClientConfig'
    'bS=config/CommonConfig'
    'bT=config/ServerConfig'
    'bU=event/EventHandler'
    'bV=event/ClientEventHandler'
    'bW=event/PlayerEventHandler'
    'bX=event/EntityEventHandler'
    'bY=event/RenderEventHandler'
    'bZ=event/InteractEventHandler'
)

$mapping = @{}
foreach ($line in $mapLines) {
    $parts = $line -split '=', 2
    $mapping[$parts[0]] = $parts[1]
}

$files = Get-ChildItem $srcBase -Filter '*.java' -Recurse
$totalImportReplacements = 0
$totalClassReplacements = 0
$filesModified = 0

foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
    $original = $content
    
    foreach ($key in $mapping.Keys) {
        $value = $mapping[$key]
        $fqcn = "com.dev.am.immersive_restraints.$($value -replace '/', '.')"
        $className = Split-Path $value -Leaf
        
        $oldImport = "import abductionmod.content.$key;"
        $newImport = "import $fqcn;"
        if ($content.Contains($oldImport)) {
            $content = $content.Replace($oldImport, $newImport)
            $totalImportReplacements++
        }
        
        $oldRef = "abductionmod.content.$key"
        $newRef = $fqcn
        if ($content.Contains($oldRef) -and -not $content.Contains($oldImport)) {
            $content = $content.Replace($oldRef, $newRef)
        }
    }
    
    $content = $content -replace 'package com\.dev\.am\.immersive_restraints\.(\w+)\.(\w+);', 'package com.dev.am.immersive_restraints.$1.$2;'
    
    if ($content -ne $original) {
        [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.Encoding]::UTF8)
        $filesModified++
    }
}

Write-Output "Files processed: $($files.Count)"
Write-Output "Files modified: $filesModified"
Write-Output "Import replacements: $totalImportReplacements"