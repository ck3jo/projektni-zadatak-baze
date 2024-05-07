<div class="flex justify-center py-10 rounded-t-lg overflow-hidden">
    <table class="px-5 pt-10 w-4/5 rounded-t-lg">
        <thead class="bg-gray-800">
            <th class="rounded-tl-xl py-3 font-bold text-white w-1/4">Ime tima</th>
            <th class="py-3 font-bold text-white w-1/4">Rang pozicija</th>
            <th class="py-3 font-bold text-white w-1/4">Broj Major trofeja</th>
            <th class="rounded-tr-xl py-3 font-bold text-white w-1/4">Region</th>
        </thead>
        <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600">
            @foreach ($teams as $team)
                <tr class="text-white text-center">
                    <td class="py-2">{{ $team->ImeTima }}</td>
                    <td class="py-2">{{ $team->RangPozicija }}</td>
                    <td class="py-2">{{ $team->MajorTrofeji }}</td>
                    <td class="py-2">{{ $team->Region }}</td>
                </tr>
            @endforeach
        </tbody>
    </table>
</div>
