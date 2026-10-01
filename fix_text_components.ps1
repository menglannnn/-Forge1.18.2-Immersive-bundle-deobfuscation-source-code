[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
[Console]::InputEncoding = [System.Text.Encoding]::UTF8

$baseDir = "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1\src\main\java\com\dev\am\immersive_restraints"

$fixedCount = 0

Get-ChildItem -LiteralPath $baseDir -Recurse -Filter "*.java" | ForEach-Object {
    $file = $_
    $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
    $modified = $false

    if ($content -match 'import net\.minecraft\.network\.chat\.TextComponent;') {
        $content = $content -replace 'import net\.minecraft\.network\.chat\.TextComponent;', 'import net.minecraft.network.chat.Component;'
        $modified = $true
    }

    if ($content -match 'import net\.minecraft\.network\.chat\.TranslatableComponent;') {
        $content = $content -replace 'import net\.minecraft\.network\.chat\.TranslatableComponent;', 'import net.minecraft.network.chat.Component;'
        $modified = $true
    }

    $newContent = [regex]::Replace($content, 'new TextComponent\(', 'Component.literal(')
    if ($newContent -ne $content) { $modified = $true; $content = $newContent }

    $newContent = [regex]::Replace($content, 'new TranslatableComponent\(', 'Component.translatable(')
    if ($newContent -ne $content) { $modified = $true; $content = $newContent }

    if ($modified) {
        [System.IO.File]::WriteAllText($file.FullName, $content, (New-Object System.Text.UTF8Encoding $true))
        $fixedCount++
        Write-Host "Fixed text components in: $($file.Name)"
    }
}

Write-Host ""
Write-Host "Total files fixed: $fixedCount"