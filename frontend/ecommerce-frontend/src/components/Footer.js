import Link from "next/link";

export default function Footer() {
    return (
        <div className="p-10 w-screen min-h-[30vh] flex justify-around bg-[#ededed] text-md">
            <div className="help">
                <p className="font-bold">Help</p>
                <ul className="mt-2 text-sm">
                    <li>Contact</li>
                    <li>FAQ</li>
                    <li>Find store</li>
                    <li>Complaint</li>
                </ul>
            </div>
            <div className="careers">
                <p className="font-bold">Careers</p>
                <ul className="mt-2 text-sm">
                    <li>Job list</li>
                    <li>FAQ</li>
                </ul>
            </div>
            <div className="terms">
                <p className="font-bold">Terms of use</p>
                <ul className="mt-2 text-sm">
                    <li>"rockstarr online store" terms of use</li>
                </ul>
            </div>
            <div className="privacy">
                <p className="font-bold">Privacy policy</p>
                <ul className="mt-2 text-sm">
                    <li>Privacy policy</li>
                    <li>Cookies policy</li>
                    <li>Cookies settings</li>
                </ul>
            </div>
            <div className="social-medias">
                <p className="font-bold">Follow us</p>
                <ul className="mt-2 text-sm">
                    <li>Instagram</li>
                    <li>Facebook</li>
                </ul>
            </div>
        </div>
    )
}