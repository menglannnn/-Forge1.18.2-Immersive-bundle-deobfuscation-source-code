$ErrorActionPreference = 'Continue'
$base = (Resolve-Path 'C:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1').Path
$srcBase = "$base\src\main\java\com\dev\am\immersive_restraints"

$replacements = @(
    'abductionmod.content.bb=com.dev.am.immersive_restraints.item.VulnerabilityType'
    'abductionmod.content.k=com.dev.am.immersive_restraints.animation.StruggleAnimations'
    'com.dev.am.Abduction=com.dev.am.immersive_restraints.ImmersiveRestraints'
    'com.dev.am.mod.gamerule.GameruleRegistry=com.dev.am.immersive_restraints.config.GameruleRegistry'
    'com.dev.am.mod.gamerule.ArmorIsHiddenWhenTied=com.dev.am.immersive_restraints.config.ArmorIsHiddenWhenTied'
    'com.dev.am.mod.gamerule.StruggleMinigameSpeedPercent=com.dev.am.immersive_restraints.config.StruggleMinigameSpeedPercent'
    'com.dev.am.mod.gamerule.StruggleFailStrengthPercent=com.dev.am.immersive_restraints.config.StruggleFailStrengthPercent'
    'com.dev.am.mod.gamerule.StruggleSuccessStrengthPercent=com.dev.am.immersive_restraints.config.StruggleSuccessStrengthPercent'
    'com.dev.am.mod.gamerule.UtmForEverybody=com.dev.am.immersive_restraints.config.UtmForEverybody'
)

$files = Get-ChildItem $srcBase -Filter '*.java' -Recurse
$totalReplacements = 0

foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
    $modified = $false
    
    foreach ($rep in $replacements) {
        $parts = $rep -split '=', 2
        $old = $parts[0]
        $new = $parts[1]
        if ($content.Contains($old)) {
            $content = $content.Replace($old, $new)
            $modified = $true
            $totalReplacements++
        }
    }
    
    $content = $content -replace 'import abductionmod\.content\.(\w+);', ''
    
    if ($modified) {
        [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.Encoding]::UTF8)
    }
}

Write-Output "Total replacements: $totalReplacements"
Write-Output "Remaining abductionmod references:"
$remaining = Select-String -Path "$srcBase\**\*.java" -Pattern 'abductionmod\.content\.' -ErrorAction SilentlyContinue
if ($remaining) {
    foreach ($r in $remaining) { Write-Output "  $($r.Filename):$($r.LineNumber): $($r.Line.Trim())" }
} else {
    Write-Output "  None! All references resolved."
}