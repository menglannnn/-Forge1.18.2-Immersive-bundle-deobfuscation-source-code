$projectDir = "C:\IR201\src\main\java\com\dev\am\immersive_restraints"
$files = Get-ChildItem -Path $projectDir -Filter "*.java" -Recurse

$srgToOfficial = @{
    # DataGenerator
    "m_123914_" = "addProvider"
    "m_123917_" = "run"
    
    # GagType
    "m_124288_" = "registerDefaultEffect"
    
    # CraftingContainer
    "m_6211_" = "clearContent"
    
    # ItemStack
    "m_41663_" = "enchant"
    "m_41714_" = "setHoverName"
    "m_41620_" = "copyWithCount"
    
    # ResourceManager
    "m_6540_" = "listResources"
    "m_142591_" = "getResource"
    
    # ResourceLocation
    "m_135827_" = "getNamespace"
    "m_135815_" = "getPath"
    
    # Resource
    "m_6679_" = "open"
    
    # Player
    "m_20202_" = "getLeashHolder"
    "m_142098_" = "dropLeash"
    "m_20137_" = "addTag"
    "m_20049_" = "removeTag"
    
    # TextureManager
    "m_174786_" = "getTexture"
    "m_118495_" = "register"
    
    # MissingTextureAtlasSprite
    "m_118080_" = "getMissingTexture"
    
    # DefaultPlayerSkin
    "m_118626_" = "getDefaultSkin"
    
    # SharedSuggestionProvider
    "m_82967_" = "suggest"
    
    # Entity
    "m_41788_" = "hasCustomName"
    "m_41786_" = "getCustomName"
    
    # DispenserBlock
    "m_52672_" = "registerBehavior"
    
    # Block
    "m_49796_" = "box"
    "m_49959_" = "registerDefaultState"
    "m_49966_" = "defaultBlockState"
    
    # BlockState
    "m_61090_" = "any"
    "m_61143_" = "getValue"
    "m_61122_" = "cycle"
    
    # Level
    "m_7731_" = "setBlock"
    
    # BlockBehaviour.Properties
    "m_60926_" = "of"
    "m_60978_" = "strength"
    
    # StateDefinition.Builder
    "m_61104_" = "add"
    
    # DirectionalBondageBlock / Block methods
    "m_7749_" = "getShadeBrightness"
    "m_7420_" = "isCollisionShapeFullBlock"
    "m_5940_" = "getShape"
    "m_6227_" = "use"
    "m_5573_" = "getStateForPlacement"
    "m_7926_" = "createBlockStateDefinition"
    
    # BlockPlaceContext
    "m_43719_" = "getNearestFacingDirection"
    "m_7058_" = "isSecondaryUseActive"
    
    # Shapes
    "m_83040_" = "empty"
    "m_83113_" = "join"
    "m_83048_" = "create"
    
    # BooleanOp
    "f_82695_" = "ONLY_FIRST"
    
    # Level isClientSide
    "f_46443_" = "isClientSide"
    
    # PlayerModel
    "f_103380_" = "slim"
    "f_102610_" = "crouching"
    "f_102814_" = "leftArm"
    "f_102813_" = "rightArm"
    "f_102808_" = "body"
    "f_103374_" = "leftSleeve"
    "f_103375_" = "rightSleeve"
    
    # ModelPart fields
    "f_104207_" = "visible"
    "f_104203_" = "yRot"
    "f_104205_" = "xScale"
    "f_104201_" = "yScale"
    
    # Vec3
    "f_82481_" = "z"
    "f_82479_" = "x"
    
    # EntityDataSerializers
    "f_135033_" = "ITEM_STACK"
    "f_135029_" = "FLOAT"
    "f_135035_" = "BOOLEAN"
    "f_135028_" = "INT"
    "f_135042_" = "COMPOUND_TAG"
    
    # ItemStack.EMPTY
    "f_41583_" = "EMPTY"
    
    # Entity fields
    "f_19797_" = "tickCount"
    "f_19793_" = "entityData"
    
    # Minecraft
    "f_91073_" = "level"
    
    # AbstractSoundInstance
    "f_119573_" = "volume"
    "f_119574_" = "pitch"
    
    # PlayerRenderer
    "f_115290_" = "model"
    
    # MultiLineLabel
    "m_94341_" = "create"
    "m_5770_" = "getLineCount"
    "m_6276_" = "renderCentered"
    
    # Checkbox
    "m_93840_" = "isChecked"
    
    # Screen fields
    "f_96547_" = "font"
    "f_96543_" = "width"
    "f_96544_" = "height"
    "f_95648_" = "title"
    "f_96541_" = "minecraft"
    
    # Minecraft
    "m_91395_" = "popScreen"
    
    # FormattedText
    "m_6881_" = "copy"
    
    # LockableDoorBlock
    "m_141972_" = "init"
    
    # CraftingContainer / Inventory
    "f_39320_" = "items"
    
    # ResultSlot
    "f_40162_" = "container"
    
    # Inventory
    "f_35977_" = "armor"
    
    # SoundEvents
    "f_12591_" = "ITEM_PICKUP"
    "f_12018_" = "ENTITY_ITEM_BREAK"
    "f_12639_" = "ITEM_ARMOR_EQUIP_GENERIC"
    
    # Attributes
    "f_22281_" = "ATTACK_DAMAGE"
    "f_22283_" = "ATTACK_SPEED"
    
    # AttributeModifier
    "f_41374_" = "ATTACK_DAMAGE_MODIFIER"
    "f_41375_" = "ATTACK_SPEED_MODIFIER"
    
    # Blocks
    "f_50109_" = "OAK_PLANKS"
    
    # LivingEntityRenderer
    "m_115326_" = "addLayer"
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