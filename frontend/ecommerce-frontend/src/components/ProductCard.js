export default function ProductCard({product}) {

    return (
        <div className="flex flex-col max-w-[250px]">
            <img className="min-h-[320px] max-h-[320px] min-w-[250px] max-w-[250px] object-cover" src={product.imageUrl} alt={product.description}></img>
            <h3 className="text-center text-lg text-[--primary-text-color] font-[400]">{product.name}</h3>
            <p className="text-center text-base text-[--secondary-text-color] font-[300]">{product.price} USD</p>
        </div>
    )
}