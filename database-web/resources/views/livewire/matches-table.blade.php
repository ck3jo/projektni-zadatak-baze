<div class="flex justify-center py-10 rounded-t-lg overflow-hidden">
    <table class="px-5 pt-10 w-5/6 rounded-t-lg">
        <thead class="bg-gray-800">
            <th class="rounded-tl-xl py-3 font-bold text-white">Ime prvog tima</th>
            <th class="py-3 font-bold text-white">Ime drugog tima</th>
            <th class="py-3 font-bold text-white">Ime turnira</th>
            <th class="py-3 font-bold text-white">Broj mapa</th>
            <th class="py-3 font-bold text-white">Rezultat</th>
            <th class="rounded-tr-xl py-3 font-bold text-white">Datum meča</th>
        </thead>
        <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
            @foreach ($matches as $match)
                <tr class="text-white text-center">
                    <td class="py-2">{{ $match->getFirstTeamName() }}</td>
                    <td class="py-2">{{ $match->getSecondTeamName() }}</td>
                    <td class="py-2">{{ $match->getTournamentName() }}</td>
                    <td class="py-2">{{ $match->BrojMapa }}</td>
                    <td class="py-2">{{ $match->Rezultat }}</td>
                    <td class="py-2">{{ $match->DatumMeca }}</td>
                </tr>
            @endforeach
        </tbody>
    </table>
</div>
