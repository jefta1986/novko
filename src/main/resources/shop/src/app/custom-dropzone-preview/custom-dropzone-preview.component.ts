import {NgxDropzonePreviewComponent} from 'ngx-dropzone';
import {Component, OnInit} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'custom-dropzone-preview',
  templateUrl: `./custom-dropzone-preview.component.html`,
  styleUrls: ['./custom-dropzone-preview.component.css'],
  providers: [
    {
      provide: NgxDropzonePreviewComponent,
      useExisting: CustomDropzonePreviewComponent
    }
  ]
})
export class CustomDropzonePreviewComponent extends NgxDropzonePreviewComponent implements OnInit {

  public imageSrc: string | ArrayBuffer = '';

  constructor(sanitizer: DomSanitizer) {
    super(sanitizer);
  }

  ngOnInit(): void {
    this.renderImage();
  }

  private renderImage() {
    if (this.file.name.includes('http')) {
      this.imageSrc = this.file.name;
    } else {
      this.readFile()
        .then(img => setTimeout(() => this.imageSrc = img))
        .catch(err => console.error(err));
    }
  }
}


