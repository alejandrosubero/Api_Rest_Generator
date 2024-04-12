import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutSammaryComponent } from './about-sammary.component';

describe('AboutSammaryComponent', () => {
  let component: AboutSammaryComponent;
  let fixture: ComponentFixture<AboutSammaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AboutSammaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AboutSammaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
