<div class="flex justify-center py-10 rounded-t-lg overflow-hidden">
    <table class="px-5 pt-10 w-5/6 rounded-t-lg">
        <thead class="bg-gray-800">
            <th class="rounded-tl-xl py-3 font-bold text-white">Naslov</th>
            <th class="py-3 font-bold text-white">Datum objavljivanja</th>
            <th class="rounded-tr-xl py-3 font-bold text-white">Ime autora</th>
        </thead>
        <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
            @foreach ($newsarr as $news)
                <tr class="text-white text-center">
                    <td class="py-2">{{ $news->Naslov }}</td>
                    <td class="py-2">{{ $news->getFormattedDate() }}</td>
                    <td class="py-2">{{ $news->author->Nadimak }}</td>
                </tr>
            @endforeach
        </tbody>
    </table>
</div>
