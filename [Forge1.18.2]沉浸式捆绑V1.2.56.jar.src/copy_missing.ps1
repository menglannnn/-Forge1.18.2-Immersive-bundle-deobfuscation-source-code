$ErrorActionPreference = 'Continue'
$base = (Resolve-Path 'C:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1').Path
$srcBase = "$base\src\main\java\com\dev\am\immersive_restraints"
$cfrDir = 'c:\Users\小亮傻逼\Desktop\cfr_output\abductionmod\content'

$mapLines = @(
    'bx=mechanic/BondageManager'
    'bw=mechanic/NBTManager'
    'bs=gag/GagTransformer'
    'bl=mechanic/StruggleLogic'
    'bm=mechanic/BondageState'
    'bn=mechanic/HandBindLogic'
    'bo=mechanic/LegBindLogic'
    'bq=mechanic/BlindfoldLogic'
    'bt=mechanic/LegTyingLogic'
    'bu=client/AgeConfirmScreen'
    'bv=client/WardrobeScreen'
    'bz=item/ArmorDrawnProperty'
    'bh=util/TooltipFormatter'
    'bi=util/TextComponentUtil'
    'bj=network/ChannelHandler'
    'bk=network/PacketEncoder'
    'aL=network/TyingPacket'
    'aM=network/UntyingPacket'
    'bP=blockentity/BlockEntityRenderer'
    'bY=event/RenderEventHandler'
    'bX=event/EntityEventHandler'
    'ac_0=item/Vulnerability'
    'e_0=item/ResistanceLevel'
)

$copied = 0
foreach ($line in $mapLines) {
    $parts = $line -split '=', 2
    $key = $parts[0]
    $targetPath = $parts[1]
    
    $srcFile = Join-Path $cfrDir "$key.java"
    $targetDir = "$srcBase\$((Split-Path $targetPath -Parent) -replace '/', '\')"
    $targetName = Split-Path $targetPath -Leaf
    $targetFile = "$targetDir\$targetName.java"
    
    if ((Test-Path $srcFile) -and -not (Test-Path $targetFile)) {
        if (-not (Test-Path $targetDir)) {
            New-Item -ItemType Directory -Path $targetDir -Force | Out-Null
        }
        
        $content = [System.IO.File]::ReadAllText($srcFile, [System.Text.Encoding]::UTF8)
        $pkgName = "com.dev.am.immersive_restraints.$($targetPath -replace '/', '.' -replace '\\.[^.]+$', '')"
        $content = $content -replace 'package abductionmod\.content;', "package $pkgName;"
        $content = $content -replace '"am"', '"immersive_restraints"'
        
        [System.IO.File]::WriteAllText($targetFile, $content, [System.Text.Encoding]::UTF8)
        $copied++
        Write-Output "Copied: $key -> $targetPath"
    } elseif (Test-Path $targetFile) {
        Write-Output "Already exists: $targetPath"
    } else {
        Write-Output "Source not found: $key.java"
    }
}

Write-Output "`nTotal copied: $copied"