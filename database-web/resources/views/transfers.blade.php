<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <!-- fontovi -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300..800;1,300..800&display=swap" rel="stylesheet">

        <title>Pregled transfera</title>
        @vite('resources/css/app.cs')
    </head>
    <body class="pattern-dots pattern-slate-800 pattern-bg-slate-900 pattern-opacity-100 pattern-size-4">
        <livewire:navbar />
        <livewire:transfers-table />
    </body>
</html>