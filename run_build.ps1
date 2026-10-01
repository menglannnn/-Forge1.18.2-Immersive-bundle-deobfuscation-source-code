$env:JAVA_HOME = 'C:\Program Files\Java\jdk-17'
$env:PATH = "C:\Program Files\Java\jdk-17\bin;$env:PATH"
$env:GRADLE_USER_HOME = 'C:\GradleHome'
$projectDir = $PSScriptRoot
Set-Location $projectDir
Write-Host "=== BUILD START ==="
Write-Host "Project dir: $projectDir"
& "$projectDir\gradlew.bat" --no-daemon --stacktrace compileJava 2>&1 | ForEach-Object { Write-Host $_ }
Write-Host "=== BUILD END ==="