'use client'

import React, {useState, useEffect} from "react";

import Header from "@/components/Header";
import Footer from "@/components/Footer";
import ProductCard from "@/components/ProductCard";

export default function HomePage() {

  const [products, setProducts] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/products')
      .then(response => response.json())
      .then(json => setProducts(json))
      .then(json => console.log(json))
      .catch(error => console.error(error));
  }, []);

  return (
    <div className="bg-white text-black font-lato">
      <Header />
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

      <Footer />
    </div>
  );
}