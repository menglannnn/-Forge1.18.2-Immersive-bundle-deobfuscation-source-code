$projectDir = "C:\IR201\src\main\java\com\dev\am\immersive_restraints"
$files = Get-ChildItem -Path $projectDir -Filter "*.java" -Recurse

$srgToOfficial = @{
    # Registry
    "m_7745_" = "get"
    "f_122824_" = "BLOCK"
    
    # Block / BlockItem
    "m_5456_" = "asItem"
    "m_40614_" = "getBlock"
    
    # BooleanProperty
    "m_61465_" = "create"
    
    # BlockBehaviour.Properties
    "m_60955_" = "noOcclusion"
    
    # BlockStateProperties
    "f_61372_" = "FACING"
    "f_61446_" = "OPEN"
    
    # BlockStateOwner
    "f_49792_" = "stateDefinition"
    
    # DoorBlock fields
    "f_52730_" = "HALF"
    "f_52726_" = "FACING"
    "f_52727_" = "OPEN"
    "f_52728_" = "HINGE"
    "f_52729_" = "POWERED"
    
    # BlockPos
    "m_6625_" = "above"
    "m_7494_" = "below"
    "m_121878_" = "asLong"
    
    # BlockHitResult
    "m_82434_" = "getDirection"
    
    # Direction
    "m_122424_" = "getOpposite"
    
    # Level
    "m_5594_" = "playSound"
    
    # Block / LootTable
    "m_7381_" = "getDrops"
    
    # CropBlock
    "m_7959_" = "getAgeProperty"
    
    # Minecraft
    "m_91089_" = "getSingleplayerServer"
    
    # Ingredient
    "m_43929_" = "of"
    
    # RecipeProvider / AnimationHelper
    "m_176670_" = "shaped"
    "m_176602_" = "hasItem"
    "m_125977_" = "has"
    "m_142284_" = "unlockedBy"
    "m_176498_" = "save"
    "m_176531_" = "buildRecipes"
    "m_176710_" = "shapeless"
    "m_176700_" = "slab"
    
    # SimpleCookingRecipeBuilder
    "m_126272_" = "smelting"
    
    # LootTables
    "m_79202_" = "validate"
    
    # LootContextParamSets
    "f_81421_" = "EMPTY"
    "f_81411_" = "CHEST"
    
    # PlayerModel fields
    "f_103378_" = "jacket"
    "f_103376_" = "leftPants"
    "f_103377_" = "rightPants"
    "f_102809_" = "hat"
    
    # HumanoidModel
    "f_102814_" = "leftArm"
    "f_102813_" = "rightArm"
    
    # Screen / AbstractWidget
    "f_169369_" = "children"
    "f_93623_" = "active"
    "f_95647_" = "title"
    "f_95649_" = "callback"
    
    # MultiLineLabel
    "f_94331_" = "EMPTY"
    
    # Input / KeyboardInput fields
    "f_108573_" = "jumping"
    "f_108570_" = "up"
    "f_108571_" = "down"
    "f_108572_" = "left"
    "f_108567_" = "moveForward"
    "f_108566_" = "moveStrafe"
    "f_108568_" = "forwardImpulse"
    "f_108569_" = "leftImpulse"
    
    # Minecraft
    "f_91076_" = "crosshairPickEntity"
    
    # Vector3f
    "f_122223_" = "YP"
    "f_122225_" = "XP"
    
    # DefaultVertexFormat
    "f_85819_" = "POSITION_COLOR_TEX_LIGHTMAP_NORMAL"
    "f_85817_" = "POSITION_COLOR"
    
    # HumanoidArmorLayer
    "f_117071_" = "innerModel"
    
    # LivingEntityRenderer
    "f_115291_" = "layers"
    
    # SoundEvents
    "f_11678_" = "ITEM_EMPTY"
    
    # Items
    "f_42616_" = "BARRIER"
    
    # AABB / Size
    "f_20378_" = "size"
    
    # InGameUi (Gui)
    "f_92977_" = "width"
    "f_92978_" = "height"
    
    # ServerData
    "f_105363_" = "motd"
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