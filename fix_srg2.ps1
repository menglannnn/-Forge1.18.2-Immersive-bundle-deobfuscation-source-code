$projectDir = "C:\IR201\src\main\java\com\dev\am\immersive_restraints"
$files = Get-ChildItem -Path $projectDir -Filter "*.java" -Recurse

$srgToOfficial = @{
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
    "m_130940_" = "withStyle"
    "m_130944_" = "withStyle"
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
    "m_46443_" = "isClientSide"
    "m_60734_" = "getBlock"
    "m_61124_" = "setValue"
    "m_82127_" = "literal"
    "m_82129_" = "argument"
    "m_91087_" = "getInstance"
    "m_91290_" = "getEntityRenderDispatcher"
    "m_91268_" = "getWindow"
    "m_114382_" = "getRenderer"
    "m_7200_" = "getModel"
    "m_8009_" = "setAllVisible"
    "m_102872_" = "setAllVisible"
    "m_7695_" = "renderToBuffer"
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
    "m_85836_" = "pushPose"
    "m_85849_" = "popPose"
    "m_166856_" = "translate"
    "m_85845_" = "mulPose"
    "m_85837_" = "translate"
    "m_85913_" = "getInstance"
    "m_85915_" = "begin"
    "m_85914_" = "end"
    "m_166779_" = "begin"
    "m_5483_" = "vertex"
    "m_7421_" = "uv"
    "m_6122_" = "color"
    "m_5752_" = "endVertex"
    "m_27625_" = "orthographic"
    "m_122240_" = "rotationDegrees"
    "m_115184_" = "getArmorFoilBuffer"
    "m_110473_" = "armorCutoutNoCull"
    "m_135353_" = "defineId"
    "m_135370_" = "get"
    "m_135381_" = "set"
    "m_135372_" = "define"
    "m_130259_" = "readInt"
    "m_130077_" = "writeInt"
    "m_7856_" = "init"
    "m_172820_" = "getPositionShader"
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