import Link from "next/link";

export default function ProductCard({product}) {

    return (
        <div className="flex flex-col max-w-[250px]">
            <Link href={`/products/${product.id}`}>
                <img className="min-h-[320px] max-h-[320px] min-w-[250px] max-w-[250px] object-cover opacity-100 hover:opacity-75 transition duration-200" src={product.imageUrl} alt={product.description}></img>
            </Link>
            <h3 className="text-center text-lg text-[--primary-text-color] font-[400] opacity-100 hover:opacity-75 transition duration-200">{product.name}</h3>
            <p className="text-center text-base text-[--secondary-text-color] font-[300]">{product.price} USD</p>
         </div>
    )
}