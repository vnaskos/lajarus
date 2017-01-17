
package com.bugbusters.lajarus.controller;

import com.bugbusters.lajarus.exception.StorageFileNotFoundException;
import com.bugbusters.lajarus.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/file")
public class ProfilePictureUploadController {

    @Autowired
    private StorageService storageService;

    /**
     * Enlist all uploaded file paths (for debugging reasons)
     * 
     * @param model
     * @return every uploaded filepath
     * @throws IOException 
     */
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(ProfilePictureUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    /**
     * Get a specific file
     * Usable as filter upon retrieval request
     * 
     * @param filename requested file
     * @return 
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    /**
     * Upload file handler, retrieve and store the uploaded file
     * Usable as filter upon upload
     * 
     * @param file
     * @param redirectAttributes
     * @return 
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    /**
     * Error handler for failed storage action
     * 
     * @param exc
     * @return HTTP code
     */
    @ExceptionHandler (StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}