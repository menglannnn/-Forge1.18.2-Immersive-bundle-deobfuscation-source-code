$projectDir = "C:\IR201\src\main\java\com\dev\am\immersive_restraints"
$files = Get-ChildItem -Path $projectDir -Filter "*.java" -Recurse

$srgToOfficial = @{
    # GameRules
    "m_46215_" = "getInt"
    "m_46189_" = "register"
    "m_46250_" = "create"
    "m_46312_" = "create"
    
    # CommandSourceStack
    "m_81352_" = "sendFailure"
    "m_81377_" = "getLevel"
    
    # FriendlyByteBuf
    "m_130136_" = "readUtf"
    "m_130072_" = "writeUtf"
    
    # Mth
    "m_14036_" = "clamp"
    "m_14139_" = "lerp"
    "m_14195_" = "sqrt"
    
    # Level/ServerLevel
    "m_6907_" = "players"
    "m_142572_" = "getServer"
    "m_7726_" = "getChunkSource"
    "m_8394_" = "broadcastAndSend"
    "m_6815_" = "getEntity"
    "m_8791_" = "getEntity"
    "m_45517_" = "getBrightness"
    "m_45955_" = "getNearbyEntities"
    
    # BlockPos
    "m_123341_" = "getX"
    "m_123342_" = "getY"
    "m_123343_" = "getZ"
    
    # Entity dimensions/position
    "m_20205_" = "getBbWidth"
    "m_20206_" = "getBbHeight"
    "m_20256_" = "setDeltaMovement"
    "m_20184_" = "getDeltaMovement"
    "m_82520_" = "add"
    "m_20299_" = "getEyePosition"
    "m_7939_" = "getLeashOffset"
    "m_31748_" = "getPos"
    "m_31844_" = "create"
    "m_7084_" = "setAddedToWorld"
    "m_6086_" = "getBlockLightLevel"
    "m_6135_" = "tick"
    "m_6038_" = "baseTick"
    "m_8127_" = "dropLeash"
    "m_19983_" = "spawnAtLocation"
    "m_19998_" = "spawnAtLocation"
    "m_7197_" = "add"
    "m_7500_" = "isSpectator"
    "m_41613_" = "getUseDuration"
    "m_7383_" = "getStyle"
    "m_20145_" = "isInvisibleTo"
    "m_20177_" = "isInvisibleTo"
    "m_142538_" = "getBoundingBox"
    "m_108564_" = "getModelName"
    
    # CompoundTag
    "m_128362_" = "putUUID"
    "m_128403_" = "hasUUID"
    "m_128342_" = "getUUID"
    "m_128425_" = "contains"
    
    # Tag
    "m_6426_" = "copy"
    
    # NbtUtils
    "m_129239_" = "readBlockPos"
    
    # PlayerList
    "m_11314_" = "getPlayers"
    
    # Inventory
    "m_36054_" = "add"
    "m_6641_" = "getMaxStackSize"
    "m_5866_" = "getMaxStackSize"
    
    # EnchantmentHelper
    "m_44920_" = "hasBindingCurse"
    
    # BehaviorUtils
    "m_22613_" = "throwItem"
    
    # ItemStack
    "m_150942_" = "isSameItemSameTags"
    
    # Registry
    "m_7981_" = "getKey"
    
    # EntityArgument
    "m_91466_" = "player"
    "m_91474_" = "getPlayer"
    
    # TargetingConditions
    "m_148353_" = "forCombat"
    "m_26883_" = "range"
    "m_26888_" = "selector"
    
    # RenderType
    "m_110475_" = "leash"
    "m_110467_" = "entityTranslucent"
    
    # PoseStack
    "m_85850_" = "last"
    "m_85861_" = "pose"
    
    # MultiBufferSource
    "m_6299_" = "getBuffer"
    
    # ClientboundSetEntityLinkPacket
    "m_133172_" = "getEntityId"
    "m_133175_" = "getDestEntityId"
    
    # EntityRendererProvider
    "m_174009_" = "create"
    
    # RenderSystem
    "m_69465_" = "disableDepthTest"
    "m_69482_" = "enableDepthTest"
    "m_172817_" = "getPositionColorShader"
    
    # Gui / GuiGraphics
    "m_93208_" = "drawString"
    
    # ItemRenderer
    "m_174235_" = "renderGuiItem"
    
    # LivingEntityRenderer
    "m_115338_" = "getOverlayCoords"
    "m_6931_" = "getFlipDegrees"
    "m_5933_" = "isShaking"
    
    # ClientPacketListener fields
    "f_104889_" = "level"
    
    # Entity fields
    "f_19854_" = "xOld"
    "f_19855_" = "yOld"
    "f_19856_" = "zOld"
    
    # Vec3 fields
    "f_82480_" = "y"
    
    # Registry fields
    "f_122827_" = "ITEM"
    
    # InGameUi / Gui fields
    "f_92986_" = "minecraft"
    "f_91062_" = "font"
    "f_91074_" = "player"
    "f_91066_" = "options"
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