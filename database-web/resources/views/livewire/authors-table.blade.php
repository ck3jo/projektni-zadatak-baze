<div class="flex justify-center py-10 rounded-t-lg overflow-hidden">
    <table class="px-5 pt-10 w-3/4 rounded-t-lg">
        <thead class="bg-gray-800">
            <th class="rounded-tl-xl py-3 font-bold text-white w-1/3">Ime</th>
            <th class="py-3 font-bold text-white w-1/3">Nadimak</th>
            <th class="rounded-tr-xl py-3 font-bold text-white w-1/3">Prezime</th>
        </thead>
        <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
            @foreach ($authors as $author)
                <tr class="text-white text-center">
                    <td class="py-2">{{ $author->Ime }}</td>
                    <td class="py-2">{{ $author->Nadimak }}</td>
                    <td class="py-2">{{ $author->Prezime }}</td>
                </tr>
            @endforeach
        </tbody>
    </table>
    <!-- accordion menu for later -->
</div>
