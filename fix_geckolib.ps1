[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
[Console]::InputEncoding = [System.Text.Encoding]::UTF8

$baseDir = "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1\src\main\java\com\dev\am\immersive_restraints"

$fixedCount = 0

Get-ChildItem -LiteralPath $baseDir -Recurse -Filter "*.java" | ForEach-Object {
    $file = $_
    $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
    $modified = $false

    if ($content -match 'software\.bernie\.geckolib3') {
        $content = $content -replace 'software\.bernie\.geckolib3', 'software.bernie.geckolib'
        $modified = $true
    }

    if ($content -match 'GeckoLibUtil\.') {
        $content = $content -replace 'GeckoLibUtil\.getGeoModelForClass\(', 'GeckoLibCache.getGeoModelForClass('
        $content = $content -replace 'GeckoLibUtil\.getGeoModelForTexture\(', 'GeckoLibCache.getGeoModelForTexture('
        $content = $content -replace 'GeckoLibUtil\.getAnimationForClass\(', 'GeckoLibCache.getAnimationForClass('
        $modified = $true
    }

    if ($content -match 'IAnimatable') {
        $content = $content -replace 'IAnimatable', 'Animatable'
        $modified = $true
    }

    if ($content -match 'AnimationController\.AnimationState') {
        $modified = $true
    }

    if ($modified) {
        [System.IO.File]::WriteAllText($file.FullName, $content, (New-Object System.Text.UTF8Encoding $true))
        $fixedCount++
        Write-Host "Fixed GeckoLib in: $($file.Name)"
    }
}

Write-Host ""
Write-Host "Total files fixed: $fixedCount"