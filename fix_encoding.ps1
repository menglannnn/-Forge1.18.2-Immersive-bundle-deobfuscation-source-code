$srcDir = "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1\src\main\java"
$gbk = [System.Text.Encoding]::GetEncoding("GBK")
$utf8 = New-Object System.Text.UTF8Encoding($false)
$count = 0
$errorCount = 0

Get-ChildItem $srcDir -Recurse -Filter "*.java" | ForEach-Object {
    $file = $_.FullName
    try {
        $bytes = [System.IO.File]::ReadAllBytes($file)
        $hasBadBytes = $false
        for ($i = 0; $i -lt $bytes.Length; $i++) {
            if ($bytes[$i] -eq 0x80 -and $i -gt 0 -and $bytes[$i-1] -ge 0x80) {
                $hasBadBytes = $true
                break
            }
        }
        if ($hasBadBytes) {
            $text = $gbk.GetString($bytes)
            $utf8Bytes = $utf8.GetBytes($text)
            [System.IO.File]::WriteAllBytes($file, $utf8Bytes)
            $count++
            Write-Output "Converted: $($_.Name)"
        }
    } catch {
        $errorCount++
        Write-Output "ERROR: $($_.Name) - $_"
    }
}

Write-Output ""
Write-Output "Total converted: $count files"
Write-Output "Errors: $errorCount files"