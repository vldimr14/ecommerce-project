'use client';

import React, { useState, useEffect, use } from "react";
import Link from "next/link";


export default function Product({ params }) {
    const { id } = use(params);

    console.log("Product ID:", id);

    const [product, setProduct] = useState(null);
    
    useEffect(() => {
        if (!id) return;

        fetch(`http://localhost:8080/products/${id}`)
          .then(response => response.json())
          .then(
            json => {
                setProduct(json);
                console.log(json)
            })
          .catch(error => console.error(error));
    }, [id]);

    if (!id) return <h1>Invalid product ID</h1>;
    if (!product) {
        return <h1>Loading...</h1>
    }

    return (
        <div className="m-20 font-lato">
            <Link href="/products/"><p className="mb-5"><span className="mr-2">&#8617;</span>Go back</p></Link>
            <div className="flex gap-20">
                <img className="max-w-[420px]" src={product.imageUrl} alt={product.description}></img>
                <div>
                    <h1 className="text-4xl font-[600]">{product.name}</h1>
                    <p className="text-2xl font-[400] text-[--secondary-text-color] mt-5">{product.description}</p>
                    <p className="text-xl mt-5">Color: {product.color}</p>
                    <p className="text-xl">Size: {product.size}</p>
                    <p className="text-2xl font-[300] mt-5">{product.price} USD</p>
                    <button className="btn mt-10">Add to a cart</button>
                </div>
            </div>
        </div>
    )
}