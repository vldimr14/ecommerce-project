import Link from "next/link";

export default function Header() {
    return (
        <div className="p-2 w-screen flex justify-start items-center shadow-md sticky top-0 bg-white">
            <span className="w-[30%] text-4xl font-[300] logo text-center"><Link href={"/products"}>rockstarr</Link></span>
            <ul className="categories w-[60%] flex justify-around text-md">
                <li>WOMEN</li>
                <li>MEN</li>
                <li>HOME</li>
                <li><Link href={"/products"}>ALL PRODUCTS</Link></li>
            </ul>
        </div>
    )
}