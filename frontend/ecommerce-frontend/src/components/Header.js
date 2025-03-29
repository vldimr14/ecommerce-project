'use client'

import Link from "next/link";
import { useSearchParams } from "next/navigation";

export default function Header() {
    
    function search(formData) {
        const query = formData.get("query");

        const request = `http://localhost:8080/products/search?page=0&search=${query}`;
    }

    return (
        <div className="p-2 w-screen flex justify-start items-center shadow-md sticky top-0 bg-white">
            <span className="w-[30%] text-4xl font-[300] logo text-center"><Link href={"/products"}>rockstarr</Link></span>
            <ul className="categories w-[60%] flex justify-around text-md">
                <li>WOMEN</li>
                <li>MEN</li>
                <li>HOME</li>
                <li><Link href={"/products"}>ALL PRODUCTS</Link></li>
            </ul>
            <form action={search}>
                <input name="query" className="border border-black p-2 rounded-md" placeholder="search"></input>
            </form>
        </div>
    )
}