$projectDir = "C:\IR201\src\main\java\com\dev\am\immersive_restraints"
$files = Get-ChildItem -Path $projectDir -Filter "*.java" -Recurse

$srgToOfficial = @{
    # Screen methods
    "m_142416_" = "addRenderableWidget"
    "m_6305_" = "render"
    "m_7333_" = "renderBackground"
    "m_7043_" = "isPauseScreen"
    "m_96624_" = "onClose"
    "m_7379_" = "clearWidgets"
    
    # InventoryScreen
    "m_98850_" = "renderEntityInInventory"
    
    # Window
    "m_85441_" = "getWidth"
    "m_85442_" = "getHeight"
    "m_85439_" = "getWindow"
    
    # RenderSystem
    "m_69481_" = "disableCull"
    
    # Entity rotation
    "m_146908_" = "getYRot"
    "m_146909_" = "getXRot"
    
    # PotionUtils
    "m_43579_" = "getPotion"
    
    # SharedSuggestionProvider
    "m_82970_" = "suggest"
    
    # EntityArgument
    "m_91470_" = "players"
    "m_91477_" = "getEntities"
    
    # ItemStack
    "m_41611_" = "getHoverName"
    
    # CommandSourceStack
    "m_6761_" = "hasPermission"
    
    # ServerLevel
    "m_177941_" = "getServerLevel"
    
    # MobEffectInstance
    "m_19557_" = "getDuration"
    
    # MobEffect / MobEffects
    "m_6584_" = "isDurationEffectTick"
    "m_6742_" = "applyEffectTick"
    "m_21124_" = "getEffect"
    "m_19472_" = "addAttributeModifier"
    
    # ItemArgument
    "m_120963_" = "getItem"
    "m_120979_" = "resolve"
    
    # InputConstants
    "m_84830_" = "isKeyDown"
    
    # Style
    "m_130938_" = "withStyle"
    "m_131162_" = "withBold"
    "m_131155_" = "withItalic"
    "m_131140_" = "withColor"
    "m_131142_" = "withClickEvent"
    "m_131144_" = "withHoverEvent"
    
    # Mth
    "m_14031_" = "cos"
    "m_14089_" = "sin"
    
    # PlayerModel fields
    "f_102811_" = "rightArm"
    "f_102812_" = "leftArm"
    "f_102810_" = "body"
    "f_104202_" = "xRot"
    "f_104200_" = "zRot"
    "f_104204_" = "xRot"
    
    # ModelPart
    "m_104301_" = "render"
    
    # PlayerRenderer
    "m_115326_" = "addLayer"
    
    # EntityModelProvider
    "m_174023_" = "bakeLayer"
    
    # KeyBinding
    "m_90857_" = "isDown"
    "m_90859_" = "wasPressed"
    
    # Minecraft client
    "m_91152_" = "setScreen"
    "m_91296_" = "getPartialTick"
    "m_91109_" = "getGameDirectory"
    
    # HitResult / EntityHitResult
    "m_82443_" = "getEntity"
    "m_82425_" = "getBlockPos"
    "m_82426_" = "miss"
    
    # Player
    "m_6674_" = "swing"
    "m_20182_" = "getEyePosition"
    "m_6350_" = "getLookAngle"
    "m_20096_" = "isOnGround"
    "m_5842_" = "isInWater"
    "m_20310_" = "hasPermission"
    
    # Level
    "m_8055_" = "getBlockState"
    
    # ModelLayerLocation
    "m_171293_" = "createLocation"
    
    # LayerDefinition
    "m_171565_" = "create"
    
    # MeshDefinition
    "m_171576_" = "getRoot"
    
    # PartDefinition
    "m_171599_" = "addOrReplaceChild"
    
    # CubeListBuilder
    "m_171558_" = "create"
    "m_171514_" = "texOffs"
    "m_171488_" = "addBox"
    "m_171480_" = "mirror"
    
    # CubeDeformation
    "m_171469_" = "extend"
    
    # PartPose
    "m_171419_" = "offset"
    
    # PoseStack
    "m_85841_" = "scale"
    
    # ItemEntity
    "m_32055_" = "getItem"
    
    # Enchantment
    "m_6589_" = "isCurse"
    
    # ArmorItem
    "m_40402_" = "getEquipmentSlot"
    
    # HumanoidArmorLayer
    "m_117125_" = "renderModel"
    "m_117128_" = "usesInnerModel"
    "m_117078_" = "getArmorModel"
    
    # ResourceLocation / TextureManager
    "m_118513_" = "register"
    
    # Options/KeyMapping fields
    "f_108578_" = "options"
    "f_92086_" = "keyLeft"
    "f_92085_" = "keyDown"
    "f_92087_" = "keyUp"
    "f_92088_" = "keyRight"
    "f_91077_" = "hitResult"
    "f_90987_" = "textureManager"
    "f_118808_" = "gameDirectory"
    
    # HoverEvent
    "f_130831_" = "SHOW_TEXT"
    
    # MobEffects fields
    "f_19610_" = "MOVEMENT_SLOWDOWN"
    "f_19604_" = "DIG_SLOWDOWN"
    
    # Attributes
    "f_22279_" = "MOVEMENT_SPEED"
    
    # OverlayTexture
    "f_118083_" = "NO_OVERLAY"
}

$totalReplacements = 0
$totalFiles = 0

foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
    $originalContent = $content
    $fileReplacements = 0
    
    foreach ($srg in $srgToOfficial.Keys) {
        $official = $srgToOfficial[$srg]
        if ($content.Contains($srg)) {
            $count = ([regex]::Matches($content, [regex]::Escape($srg))).Count
            $content = $content.Replace($srg, $official)
            $fileReplacements += $count
        }
    }
    
    if ($content -ne $originalContent) {
        [System.IO.File]::WriteAllText($file.FullName, $content, (New-Object System.Text.UTF8Encoding($false)))
        $totalFiles++
        $totalReplacements += $fileReplacements
        Write-Output "Fixed: $($file.Name) ($fileReplacements replacements)"
    }
}

Write-Output ""
Write-Output "=== SUMMARY ==="
Write-Output "Total files modified: $totalFiles"
Write-Output "Total replacements: $totalReplacements"