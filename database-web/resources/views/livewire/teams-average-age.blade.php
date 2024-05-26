<div class="flex justify-center pt-10 py-2 rounded-t-lg overflow-hidden">
    <table class="px-5 pt-10 w-1/2 rounded-t-lg">
        <thead class="bg-gray-800">
            <th class="rounded-tl-xl py-3 font-bold text-white">Ime tima</th>
            <th class="rounded-tr-xl py-3 font-bold text-white">Prosečne godine</th>
        </thead>
        <tbody class="[&>*:nth-child(odd)]:bg-gray-700 [&>*:nth-child(even)]:bg-gray-600 [&>*:last-child]:rounded-b-xl">
            @foreach ($teamsAvgAge as $teamAvgAge)
                @if ($teamAvgAge->IDTima !== null)
                <tr class="text-white text-center">
                    <td class="py-2">{{ $this->getTeamName($teamAvgAge->IDTima) }}</td>
                    <td class="py-2">{{ $teamAvgAge->ProsecneGodine }}</td>
                </tr>
                @endif
            @endforeach
        </tbody>
    </table>
</div>
