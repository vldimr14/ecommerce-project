'use client'

import React, {useState, useEffect} from "react";

import Image from "next/image";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

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
    <div>
      <Header />
      <h1>All products</h1>

      <ul>
        {products.map(product => (
          <li key={product.id}>{product.name}: {product.description}, size - {product.size} - {product.price}$</li>
        ))}
      </ul>

      <Footer />
    </div>
  );
}