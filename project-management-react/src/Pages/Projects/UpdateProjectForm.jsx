import React from 'react';
import {useDispatch} from "react-redux";
import {useForm} from "react-hook-form";
import {updateProject} from "@/Redux/Project/Action.js";
import {Form, FormControl, FormField, FormItem, FormMessage} from "@/components/ui/form.jsx";
import {Input} from "@/components/ui/input.jsx";
import {Button} from "@/components/ui/button.jsx";
import {tags} from "@/Pages/ProjectList/ProjectList.jsx";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select.jsx";
import {Cross1Icon} from "@radix-ui/react-icons";

const UpdateProjectForm = ({projectId}) => {

    const dispatch = useDispatch();

    const form = useForm({
        defaultValues: {
            projectName: "",
            description: "",
            category: "",
            tags: [],
        }
    });

    const onSubmit = (data) => {
        if(projectId) {
            dispatch(
                updateProject({
                    projectName: data.projectName,
                    description: data.description,
                    category: data.category,
                    tags: data.tags,
                }, {projectId})
            );
            console.log("Update Project Data", data);
            window.location.reload();
        } else {
            console.log("Project ID not found")
        }
    }

    const handleTagsChange = (newValue) => {
        const currentTags = form.getValues("tags")
        const updatedTags = currentTags.includes(newValue)?
            currentTags.filter(tag => tag.name !== newValue) : [...currentTags, newValue];

        form.setValue("tags", updatedTags);
    }

    return (
        <div>
            <Form {...form}>
                <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
                    <FormField control = {form.control}
                               name="projectName"
                               render={({field}) => <FormItem>
                                   <FormControl>
                                       <Input{...field}
                                             type="text"
                                             className="border w-full border-gray-700 py-5 px-5"
                                             placeholder="Project Name"/>
                                   </FormControl>
                                   <FormMessage>

                                   </FormMessage>
                               </FormItem>}
                    />

                    <FormField control = {form.control}
                               name="description"
                               render={({field}) => <FormItem>
                                   <FormControl>
                                       <Input{...field}
                                             type="text"
                                             className="border w-full border-gray-700 py-5 px-5"
                                             placeholder="Description"/>
                                   </FormControl>
                                   <FormMessage>

                                   </FormMessage>
                               </FormItem>}
                    />

                    <FormField control = {form.control}
                               name="category"
                               render={({field}) => <FormItem>
                                   <FormControl>
                                       <Select
                                           defaultValue="Full-Stack"
                                           value={field.value}
                                           onValueChange={(value) => field.onChange(value)}
                                       >
                                           <SelectTrigger className="w-full">
                                               <SelectValue placeholder="Category" />
                                           </SelectTrigger>
                                           <SelectContent>
                                               <SelectItem value="Full-Stack">Full Stack</SelectItem>
                                               <SelectItem value="Front-End">Front End</SelectItem>
                                               <SelectItem value="Back-End">Back End</SelectItem>
                                           </SelectContent>
                                       </Select>
                                   </FormControl>
                                   <FormMessage>

                                   </FormMessage>
                               </FormItem>}
                    />

                    <FormField control = {form.control}
                               name="tags"
                               render={({field}) => <FormItem>
                                   <FormControl>
                                       <Select
                                           onValueChange={(value) => {
                                               handleTagsChange(value)
                                           }}
                                       >
                                           <SelectTrigger className="w-full">
                                               <SelectValue placeholder="Tags" />
                                           </SelectTrigger>
                                           <SelectContent>
                                               {tags.map((item) => (
                                                   <SelectItem key={item} value={item}>
                                                       {item}
                                                   </SelectItem>
                                               ))}
                                           </SelectContent>
                                       </Select>
                                   </FormControl>
                                   {field.value.map((item) => <div key={item} onClick={() => handleTagsChange(item)} className="flex gap-1 flex-wrap">
                                           <div className="cursor-pointer flex rounded-full items-center border gap-2 px-4 py-1">
                                               <span className="text-sm">{item}</span>
                                               <Cross1Icon className="h-3 w-3"/>
                                           </div>
                                       </div>
                                   )}
                                   <FormMessage />
                               </FormItem>}
                    />

                    <Button type="submit" className="w-full mt-5">Update Project</Button>
                </form>
            </Form>
        </div>
    );

}

export default UpdateProjectForm;