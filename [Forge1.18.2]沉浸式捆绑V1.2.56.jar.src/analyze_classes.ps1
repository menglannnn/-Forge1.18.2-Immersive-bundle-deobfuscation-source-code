$srcDir = 'c:\Users\小亮傻逼\Desktop\cfr_output\abductionmod\content'
$files = Get-ChildItem $srcDir -Filter '*.java'
$totalCount = $files.Count

Write-Output "Total classes: $totalCount"
Write-Output "=== ANALYZING ALL CLASSES ==="

foreach ($file in $files) {
    $className = $file.BaseName
    $content = Get-Content $file.FullName -Raw -Encoding UTF8 -ErrorAction SilentlyContinue
    if ($content) {
        $strings = [regex]::Matches($content, '"([^"]{3,50})"') | ForEach-Object { $_.Groups[1].Value } | Where-Object { $_ -notmatch '^[/\\]' -and $_ -notmatch 'java\.' -and $_ -notmatch 'net\.minecraft' } | Select-Object -First 6
        $extends = if ($content -match 'class\s+\w+\s+extends\s+(\w+)') { $Matches[1] } else { '' }
        $impl = if ($content -match 'implements\s+([\w,\s]+?)\{') { $Matches[1].Trim() } else { '' }
        $strList = $strings -join '|'
        Write-Output "$className|ext=$extends|impl=$impl|str=$strList"
    }
}