import React, {useEffect, useState} from 'react';
import {ScrollArea} from "@/components/ui/scroll-area.jsx";
import {Avatar, AvatarFallback} from "@/components/ui/avatar.jsx";
import {Input} from "@/components/ui/input.jsx";
import {Button} from "@/components/ui/button.jsx";
import {PaperPlaneIcon} from "@radix-ui/react-icons";
import {useDispatch, useSelector} from "react-redux";
import {fetchChatByProject, fetchChatMessages, sendMessage} from "@/Redux/Chat/Action.js";
import {useParams} from "react-router-dom";

const ChatBox = () => {

    const dispatch = useDispatch();

    const {auth, chat} = useSelector(Store=>Store)

    const {id} = useParams();

    const [message, setMessage] = useState("");

    useEffect(() => {
        dispatch(fetchChatByProject(id))
    }, [dispatch, id])

    useEffect(() => {
        dispatch(fetchChatMessages(id))
    }, [dispatch, id])

    const handleSendMessage=()=>{
        dispatch(sendMessage({
            senderId: auth.users?.id,
            projectId: id,
            content: message
        }))
        setMessage("");
    }

    const handleMessageChange = (e) => {
        setMessage(e.target.value);
    }

    return (
        <div className="sticky ">
            <div className="border rounded-lg">

                <h1 className="border-b p-5">Chat Box</h1>

                <ScrollArea className="h-[32rem] w-full p-5 flex gap-3 flex-col text-left">
                    {chat.messages?.map((item, index) => (
                        item.sender.id !== auth.users.id ?
                            <div className="flex gap-2 mb-2 justify-start" key={index}>
                                <Avatar>
                                    <AvatarFallback>{item.sender.fullName[0]}</AvatarFallback>
                                </Avatar>
                                <div className="space-y-2 py-2 px-5 border rounded-ss-2xl rounded-e-xl">
                                    <p>{item.sender.fullName}</p>
                                    <p className="text-gray-300">{item.content}</p>
                                </div>
                            </div> :
                            <div className="flex gap-2 mb-2 justify-end" key={index}>
                                <Avatar>
                                    <AvatarFallback>{item.sender.fullName[0]}</AvatarFallback>
                                </Avatar>
                                <div className="space-y-2 py-2 px-5 border rounded-se-2xl rounded-s-xl">
                                    <p>{item.sender.fullName}</p>
                                    <p className="text-gray-300">{item.content}</p>
                                </div>
                            </div>
                    ))}
                </ScrollArea>

                <div className="relative p-0">
                    <Input
                        placeholder= "Type Message"
                        className="py-7 border-t outline-none focus:outline-none focus:ring-0 rounded-none border-b-0 border-x-0"
                        value={message} onChange={handleMessageChange}
                    />
                    <Button onClick={handleSendMessage} className="absolute right-2 top-3 rounded-full" size="icon" variant="ghost">
                        <PaperPlaneIcon/>
                    </Button>
                </div>
            </div>
        </div>
    )
}
export default ChatBox