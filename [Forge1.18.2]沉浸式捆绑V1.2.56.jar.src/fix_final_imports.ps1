$ErrorActionPreference = 'Continue'
$base = (Resolve-Path 'C:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1').Path
$srcBase = "$base\src\main\java\com\dev\am\immersive_restraints"

$extraReplacements = @(
    'import abductionmod.content.n;=import com.dev.am.immersive_restraints.config.NiceNTightSettings;'
    'import abductionmod.content.x;=import com.dev.am.immersive_restraints.render.RenderHelper;'
    'import abductionmod.content.c_0;=import com.dev.am.immersive_restraints.config.NiceNTightSettings;'
)

$files = Get-ChildItem $srcBase -Filter '*.java' -Recurse
$fixed = 0

foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
    $modified = $false
    
    foreach ($rep in $extraReplacements) {
        $parts = $rep -split '=', 2
        $old = $parts[0]
        $new = $parts[1]
        if ($content.Contains($old)) {
            $content = $content.Replace($old, $new)
            $modified = $true
            $fixed++
        }
    }
    
    if ($modified) {
        [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.Encoding]::UTF8)
    }
}

Write-Output "Fixed $fixed remaining imports"

$remaining = Select-String -Path "$srcBase\**\*.java" -Pattern '^import abductionmod\.content\.' -ErrorAction SilentlyContinue
if ($remaining) {
    Write-Output "Still remaining import lines:"
    foreach ($r in $remaining) { Write-Output "  $($r.Filename):$($r.LineNumber): $($r.Line.Trim())" }
} else {
    Write-Output "All import references resolved!"
}