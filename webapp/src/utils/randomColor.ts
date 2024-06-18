export const randomColorClass: string[] = [

    ' bg-amber-300', 'bg-lime-600', 'bg-teal-500', 'bg-cyan-300', 'bg-violet-500', 'bg-rose-300', 'bg-emerald-500'

]


export const getColor = (): string => {
    return randomColorClass[Math.random() * randomColorClass.length]
}