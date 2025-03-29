'use client'

import React, {useState, useEffect} from "react";
import ProductCard from "@/components/ProductCard";
import { useSearchParams } from "next/navigation";

export default function HomePage() {

  const [products, setProducts] = useState(null);
  const searchParams = useSearchParams();
  const page = searchParams.get('page');
  const sortBy = searchParams.get('sortBy');
  const search = searchParams.get('search');

  let request;

  if (sortBy !== null) {
    request = `http://localhost:8080/products?page=${page}&sortBy=${sortBy}`;
  } else if (search !== null) {
    request = `http://localhost:8080/products/search?page=${page}&search=${search}`;
  } else {
    request = `http://localhost:8080/products?page=${page}`;
  }

  useEffect(() => {
    fetch(request)
      .then(response => response.json())
      .then(json => setProducts(json))
      .then(json => console.log(json))
      .catch(error => console.error(error));
  }, []);

  return (
    <div className="bg-white text-black font-lato">
      <h1 className="text-4xl font-[700] m-10">All products</h1>

      {products == null ? 
      (<h2 className="m-10">Loading...</h2>) : 
      (<div className="products">
        <ul className="flex flex-wrap gap-5 m-10">
          {products.map(product => (
            <li key={product.key}>
              <ProductCard product={product} />
            </li>
        ))}
        </ul>
      </div>)}
    </div>
  );
}