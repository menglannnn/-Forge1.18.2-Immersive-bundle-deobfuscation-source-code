$projectDir = "C:\IR201\src\main\java\com\dev\am\immersive_restraints"
$files = Get-ChildItem -Path $projectDir -Filter "*.java" -Recurse

$srgToOfficial = @{
    # Item / ItemStack methods
    "m_41065_" = "getSerializedName"
    "m_7968_" = "getDefaultInstance"
    "m_41614_" = "isEdible"
    "m_41774_" = "shrink"
    "m_41751_" = "setTag"
    "m_41622_" = "hurtAndBreak"
    "m_41763_" = "isDamageable"
    "m_41499_" = "durability"
    "m_41503_" = "durability"
    "m_150930_" = "is"
    "m_8105_" = "getUseDuration"
    "m_6164_" = "getUseAnimation"
    "m_8120_" = "isFoil"
    "m_5922_" = "finishUsingItem"
    "m_7203_" = "use"
    "m_6225_" = "useOn"
    "m_7579_" = "canAttackBlock"
    "m_6813_" = "mineBlock"
    "m_7167_" = "getDefaultAttributeModifiers"
    "m_43723_" = "getPlayer"
    "m_21120_" = "getItemInHand"
    "m_21205_" = "getMainHandItem"
    "m_21206_" = "getOffhandItem"
    "m_36176_" = "getInventory"
    "m_21008_" = "setItemInHand"
    "m_21166_" = "getItemBySlot"
    
    # LivingEntity / Entity methods
    "m_7655_" = "getUseItem"
    "m_6469_" = "hurt"
    "m_7292_" = "addEffect"
    "m_6144_" = "isShiftKeyDown"
    "m_142081_" = "getId"
    "m_6302_" = "getName"
    "m_5584_" = "eat"
    "m_146859_" = "gameEvent"
    "m_146901_" = "blockPosition"
    "m_36324_" = "getFoodData"
    "m_38721_" = "needsFood"
    "m_150110_" = "getAbilities"
    "m_9146_" = "sendSystemMessage"
    "m_6352_" = "sendSystemMessage"
    "m_5833_" = "isSpectator"
    "m_20270_" = "distanceTo"
    "m_142469_" = "getBoundingBox"
    "m_82400_" = "inflate"
    "m_6084_" = "isAlive"
    "m_146870_" = "discard"
    "m_7398_" = "getPosition"
    "m_6210_" = "refreshDimensions"
    "m_7350_" = "onSyncedDataUpdated"
    "m_19880_" = "getTags"
    "m_20091_" = "teleportTo"
    "m_8999_" = "teleportTo"
    "m_143617_" = "teleportTo"
    "m_36335_" = "getCooldowns"
    "m_41524_" = "addCooldown"
    "m_6263_" = "playSound"
    "m_45933_" = "getEntitiesOfClass"
    "m_46469_" = "getGameRules"
    "m_46207_" = "getBoolean"
    
    # Inventory methods
    "m_6643_" = "getContainerSize"
    "m_8020_" = "getItem"
    "m_6836_" = "setItem"
    "m_150109_" = "getInventory"
    
    # CompoundTag / Tag methods
    "m_128431_" = "getAllKeys"
    "m_128423_" = "get"
    "m_128437_" = "getList"
    
    # ServerLevel / MinecraftServer methods
    "m_6846_" = "getPlayerList"
    "m_11259_" = "getPlayer"
    "m_129921_" = "getGameTime"
    "m_7873_" = "getServer"
    
    # Mob methods
    "m_6573_" = "canBeLeashed"
    "m_21524_" = "isLeashed"
    "m_21463_" = "setLeashedTo"
    "m_21455_" = "dropLeash"
    "m_21506_" = "setDelayedLeashHolderId"
    
    # InteractionResult / InteractionResultHolder
    "m_19078_" = "sidedSuccess"
    "m_19077_" = "shouldSwing"
    "m_19090_" = "success"
    "m_19092_" = "success"
    
    # Component methods
    "m_7220_" = "append"
    "m_130948_" = "withStyle"
    
    # Style methods
    "m_178520_" = "withColor"
    
    # Minecraft client methods
    "m_91297_" = "getDeltaFrameTime"
    "m_91104_" = "isPaused"
    "m_92176_" = "getCameraType"
    "m_90612_" = "isFirstPerson"
    
    # Window methods
    "m_85445_" = "getGuiScaledWidth"
    "m_85446_" = "getGuiScaledHeight"
    
    # GuiGraphics / Gui methods
    "m_93228_" = "blit"
    "m_93082_" = "getFont"
    "m_92763_" = "draw"
    "m_92852_" = "width"
    
    # RenderType
    "m_110457_" = "translucent"
    
    # Mth methods
    "m_14179_" = "lerp"
    
    # LightTexture
    "m_109885_" = "pack"
    
    # VertexConsumer methods
    "m_85982_" = "vertex"
    "m_85950_" = "color"
    "m_85969_" = "uv2"
    
    # FriendlyByteBuf
    "m_130260_" = "readCompoundTag"
    "m_130079_" = "writeCompoundTag"
    
    # Connection / Network
    "m_6198_" = "getConnection"
    
    # BlockState methods
    "m_60800_" = "getDestroySpeed"
    
    # ItemUtils
    "m_150959_" = "useItem"
    
    # TextFilter
    "m_143628_" = "handleChat"
    "m_9809_" = "filterText"
    
    
    # DamageSource fields
    "f_19319_" = "OUT_OF_WORLD"
    
    # SoundEvents fields
    "f_11772_" = "GLASS_BREAK"
    
    # Items fields
    "f_42590_" = "AIR"
    "f_42655_" = "LEAD"
    
    # Entity fields
    "f_19804_" = "entityData"
    "f_19820_" = "UUID_TAG"
    "f_19853_" = "level"
    
    # ServerPlayer fields
    "f_8906_" = "connection"
    "f_8924_" = "serverLevel"
    "f_9743_" = "player"
    
    # GameRules fields
    "f_46133_" = "RULE_KEEPINVENTORY"
    
    # GameEvent fields
    "f_157806_" = "EAT"
    
    # ChatType fields (not needed, using method)
    
    # Util fields
    "f_137441_" = "NIL_UUID"
    
    # Minecraft client fields
    "f_91066_" = "options"
    "f_20883_" = "xRotO"
    "f_20884_" = "xRot"
    
    # Player.Abilities fields
    "f_35937_" = "instabuild"
    
    # Style fields
    "f_131099_" = "EMPTY"
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