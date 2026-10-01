$projectDir = "C:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1\src\main\java\com\dev\am\immersive_restraints"
$files = Get-ChildItem -Path $projectDir -Filter "*.java" -Recurse

$srgToOfficial = @{
    # ItemStack methods
    "m_41784_" = "getOrCreateTag"
    "m_41783_" = "getTag"
    "m_41619_" = "isEmpty"
    "m_41720_" = "getItem"
    "m_41777_" = "getOrCreateTagElement"
    "m_5524_" = "getDescriptionId"
    "m_41491_" = "tab"
    "m_41487_" = "stacksTo"
    "m_6844_" = "getArmorTexture"
    "m_6883_" = "inventoryTick"
    "m_6880_" = "interactLivingEntity"
    "m_5812_" = "isFoil"
    "m_7373_" = "appendHoverText"
    "m_5965_" = "getStateForPlacement"
    "m_43722_" = "getItem"
    "m_6494_" = "render"
    "m_117386_" = "getArmorModelForPlacement"
    
    # CompoundTag methods
    "m_128359_" = "putString"
    "m_128405_" = "putInt"
    "m_128365_" = "put"
    "m_128350_" = "putFloat"
    "m_128379_" = "putBoolean"
    "m_128441_" = "contains"
    "m_128473_" = "remove"
    "m_128461_" = "getString"
    "m_128469_" = "getCompound"
    "m_128451_" = "getInt"
    "m_128457_" = "getFloat"
    "m_128471_" = "getBoolean"
    
    # Component methods
    "m_130940_" = "withStyle"
    "m_130944_" = "withStyle"
    
    # Entity/LivingEntity/Player methods
    "m_20194_" = "server"
    "m_5661_" = "sendSystemMessage"
    "m_6168_" = "getArmorSlots"
    "m_21187_" = "getRandom"
    "m_20089_" = "getPose"
    "m_6972_" = "getDimensions"
    "m_20390_" = "scale"
    "m_7998_" = "canSee"
    "m_20069_" = "onGround"
    "m_20185_" = "getX"
    "m_20186_" = "getY"
    "m_20189_" = "getZ"
    "m_183503_" = "level"
    "m_142049_" = "getId"
    "m_7755_" = "getDisplayName"
    "m_7639_" = "getEntity"
    "m_5776_" = "isClientSide"
    "m_81373_" = "getEntityOrException"
    "m_81354_" = "sendSuccess"
    "m_46003_" = "getPlayer"
    "m_45976_" = "getEntitiesOfClass"
    
    # Level methods
    "m_46443_" = "isClientSide"
    
    # BlockState methods
    "m_60734_" = "getBlock"
    "m_61124_" = "setValue"
    
    # Commands methods
    "m_82127_" = "literal"
    "m_82129_" = "argument"
    
    # Minecraft client methods
    "m_91087_" = "getInstance"
    "m_91290_" = "getEntityRenderDispatcher"
    "m_91268_" = "getWindow"
    "m_114382_" = "getRenderer"
    "m_7200_" = "getModel"
    
    # PlayerRenderer/Model methods
    "m_8009_" = "setAllVisible"
    "m_102872_" = "setAllVisible"
    "m_7695_" = "renderToBuffer"
    
    # RenderSystem methods
    "m_157183_" = "setShaderTexture0"
    "m_157425_" = "setProjectionMatrix"
    "m_157191_" = "getModelViewStack"
    "m_157182_" = "applyModelViewMatrix"
    "m_157427_" = "setShader"
    "m_157429_" = "setShaderColor"
    "m_69478_" = "enableBlend"
    "m_69464_" = "defaultBlendFunc"
    "m_69458_" = "enableDepthTest"
    "m_69453_" = "enableCull"
    "m_157456_" = "setShaderTexture"
    "m_69444_" = "enableColorWrite"
    "m_157424_" = "resetTextureMatrix"
    
    # PoseStack methods
    "m_85836_" = "pushPose"
    "m_85849_" = "popPose"
    "m_166856_" = "translate"
    "m_85845_" = "mulPose"
    "m_85837_" = "translate"
    
    # Tesselator/BufferBuilder methods
    "m_85913_" = "getInstance"
    "m_85915_" = "begin"
    "m_85914_" = "end"
    "m_166779_" = "begin"
    "m_5483_" = "vertex"
    "m_7421_" = "uv"
    "m_6122_" = "color"
    "m_5752_" = "endVertex"
    
    # Matrix4f/Vector3f methods
    "m_27625_" = "orthographic"
    "m_122240_" = "rotationDegrees"
    
    # ItemRenderer methods
    "m_115184_" = "getArmorFoilBuffer"
    "m_110473_" = "armorCutoutNoCull"
    
    # SynchedEntityData methods
    "m_135353_" = "defineId"
    "m_135370_" = "get"
    "m_135381_" = "set"
    "m_135372_" = "define"
    
    # FriendlyByteBuf methods
    "m_130259_" = "readInt"
    "m_130077_" = "writeInt"
    
    # Screen methods
    "m_7856_" = "init"
    
    # GameRenderer
    "m_172820_" = "getPositionShader"
    
    # DamageSource
    "m_7639_" = "getEntity"
    
    # DefaultVertexFormat
    "f_85819_" = "POSITION_COLOR_TEX"
    
    # EntityDataSerializers
    "f_135029_" = "FLOAT"
    "f_135035_" = "BOOLEAN"
    "f_135028_" = "INT"
    "f_135042_" = "COMPOUND_TAG"
    
    # OverlayTexture
    "f_118083_" = "NO_OVERLAY"
    
    # PlayerModel fields
    "f_103380_" = "slim"
    
    # Minecraft client fields
    "f_91074_" = "player"
    "f_91073_" = "level"
    
    # Screen fields
    "f_169369_" = "children"
    
    # Level fields
    "f_19853_" = "level"
    "f_46443_" = "isClientSide"
    
    # Entity fields
    "f_19804_" = "entityData"
    
    # EntityEvent.Size
    "f_20378_" = "oldSize"
    
    # RenderSystem
    "m_157183_" = "setShaderTexture0"
    
    # VertexFormat.Mode
    "QUADS" = "QUADS"
}

$totalReplacements = 0
$totalFiles = 0

foreach ($file in $files) {
    $content = Get-Content -Path $file.FullName -Raw -Encoding UTF8
    $originalContent = $content
    $fileReplacements = 0
    
    foreach ($srg in $srgToOfficial.Keys) {
        $official = $srgToOfficial[$srg]
        $pattern = [regex]::Escape($srg)
        $matches = [regex]::Matches($content, $pattern)
        if ($matches.Count -gt 0) {
            $content = $content -replace $pattern, $official
            $fileReplacements += $matches.Count
        }
    }
    
    if ($content -ne $originalContent) {
        Set-Content -Path $file.FullName -Value $content -Encoding UTF8 -NoNewline
        $totalFiles++
        $totalReplacements += $fileReplacements
        Write-Host "Fixed: $($file.Name) ($fileReplacements replacements)"
    }
}

Write-Host ""
Write-Host "=== SUMMARY ==="
Write-Host "Total files modified: $totalFiles"
Write-Host "Total replacements: $totalReplacements"

"Total files: $totalFiles, Total replacements: $totalReplacements" | Out-File -FilePath "C:\GradleHome\srg_fix_result.txt" -Encoding utf8