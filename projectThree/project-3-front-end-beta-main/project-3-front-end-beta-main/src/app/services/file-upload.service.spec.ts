import { HttpClientTestingModule } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { FileUploadService } from './file-upload.service';

describe('FileUploadService', () => {
  let component: FileUploadService;
  let fixture: ComponentFixture<FileUploadService>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule 
      ],
      declarations: [
        FileUploadService
      ],
    }).compileComponents();
  }));
});