<div class="flex justify-center py-10 rounded-t-lg overflow-hidden">
    <table class="px-5 pt-10 w-3/4 rounded-t-lg">
        <thead class="bg-gray-800">
            <th class="rounded-tl-xl py-3 font-bold text-white">Ime igrača</th>
            <th class="py-3 font-bold text-white">Ime starog tima</th>
            <th class="py-3 font-bold text-white">Ime novog tima</th>
            <th class="rounded-tr-xl font-bold text-white">Datum transfera</th>
        </thead>
        <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
            @foreach ($transfers as $transfer)
                <tr class="text-white text-center">
                    <td class="py-2">{{ $transfer->getPlayerName() }}</td>
                    <td class="py-2">{{ $transfer->getOldTeamName() }}</td>
                    <td class="py-2">{{ $transfer->getNewTeamName() }}</td>
                    <td class="py-2">{{ $transfer->getFormattedDate() }}</td>
                </tr>
            @endforeach
        </tbody>
    </table>
</div>
