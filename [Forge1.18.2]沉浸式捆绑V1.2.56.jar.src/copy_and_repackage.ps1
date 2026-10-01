$ErrorActionPreference = 'Stop'
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

$srcDir = 'c:\Users\小亮傻逼\Desktop\cfr_output\abductionmod\content'
$files = Get-ChildItem $srcDir -Filter '*.java'
$copiedCount = 0
$unmappedCount = 0
$unmappedNames = @()

foreach ($file in $files) {
    $className = $file.BaseName
    if ($mapping.ContainsKey($className)) {
        $targetPath = $mapping[$className]
        $targetDir = "$srcBase\$((Split-Path $targetPath -Parent) -replace '/', '\')"
        $targetName = Split-Path $targetPath -Leaf
        $targetFile = "$targetDir\$targetName.java"
        
        if (-not (Test-Path $targetDir)) {
            New-Item -ItemType Directory -Path $targetDir -Force | Out-Null
        }
        
        $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
        $pkgName = "com.dev.am.immersive_restraints.$($mapping[$className] -replace '/', '.' -replace '\\.[^.]+$', '')"
        $content = $content -replace 'package abductionmod\.content;', "package $pkgName;"
        
        [System.IO.File]::WriteAllText($targetFile, $content, [System.Text.Encoding]::UTF8)
        $copiedCount++
    } else {
        $unmappedCount++
        $unmappedNames += $className
    }
}

Write-Output "Copied and repackaged: $copiedCount classes"
Write-Output "Unmapped: $unmappedCount classes"
if ($unmappedNames.Count -gt 0) {
    Write-Output "Unmapped names: $($unmappedNames -join ', ')"
}

$dirs = Get-ChildItem $srcBase -Directory -Recurse -ErrorAction SilentlyContinue | Select-Object -ExpandProperty FullName
Write-Output "`nCreated packages:"
foreach ($d in $dirs) {
    $rel = $d.Substring($srcBase.Length + 1) -replace '\\', '.'
    Write-Output "  com.dev.am.immersive_restraints.$rel"
}